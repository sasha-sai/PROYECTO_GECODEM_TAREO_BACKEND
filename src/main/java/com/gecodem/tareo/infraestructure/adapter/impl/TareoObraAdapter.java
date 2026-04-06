package com.gecodem.tareo.infraestructure.adapter.impl;

import com.gecodem.tareo.domain.model.TrabajadorAsignado;
import com.gecodem.tareo.domain.model.TrabajadorTareoDiario;
import com.gecodem.tareo.domain.port.TareoObraPort;
import com.gecodem.tareo.infraestructure.persistence.SupervisorObraEntity;
import com.gecodem.tareo.infraestructure.persistence.TareoObraEntity;
import com.gecodem.tareo.infraestructure.persistence.UsuarioEntity;
import com.gecodem.tareo.infraestructure.repositories.TareoObraRepository;
import com.gecodem.tareo.utils.DateFormatter;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class TareoObraAdapter implements TareoObraPort {

    private final TareoObraRepository tareoObraRepository;
    private final EntityManager entityManager;

    @Override
    public void guardarTareoDiario(Long idObraAsignada, List<Long> usuarios) {

        SupervisorObraEntity obraDelSupervisor = entityManager.getReference(SupervisorObraEntity.class, idObraAsignada);

        // Generar lista de tareos con la obra asignada al supervisor y los trabajadores
        List<TareoObraEntity> asignacionesUsuarioEnTareo = usuarios.stream()
                .map(idUsuario -> {
                    UsuarioEntity usuarioEntity = entityManager.getReference(UsuarioEntity.class, idUsuario);
                    return TareoObraEntity.builder()
                            .asignacionSupervisorObra(obraDelSupervisor)
                            .usuario(usuarioEntity)
                            .fechaAsignacion(LocalDate.now().plusDays(1))
                            .build();
                })
                .toList();

         tareoObraRepository.saveAll(asignacionesUsuarioEnTareo);
    }

    @Override
    public List<TrabajadorAsignado> usuariosEnTareo(Long idObraAsignada, LocalDate fechaAsignado) {
        return tareoObraRepository.findUsuariosConTareo(idObraAsignada, fechaAsignado)
                .stream().map(this::parseEntityToTrabajadorAsignado).toList();
    }

    @Override
    public List<Long> idUsuariosEnTareo(Long idObraAsignada, LocalDate fechaAsignado) {
        return tareoObraRepository.findUsuariosConTareo(idObraAsignada, fechaAsignado)
                .stream().map(tareo -> tareo.getUsuario().getId()).toList();
    }

    @Override
    public void eliminarUsuarioDelTareo(Long idUsuario) {
        tareoObraRepository.deleteById(idUsuario);
    }

    @Override
    public List<TrabajadorTareoDiario> obtenerTrabajadoresDeTareo(Long idObraAsignada, LocalDate fechaAsignado) {
        return tareoObraRepository.findUsuariosConTareo(idObraAsignada, fechaAsignado)
                .stream().map(this::parseEntityToTrabajadorTareoDiario).toList();
    }

    @Override
    public TrabajadorTareoDiario marcarIngresoTrabajadorDeTareo(Long idTrabajador, LocalDateTime fechaIngreso) {
        TareoObraEntity trabajador = tareoObraRepository.findById(idTrabajador)
                .orElseThrow();

        trabajador.setFechaInicioDia(fechaIngreso);
        tareoObraRepository.save(trabajador);
        return parseEntityToTrabajadorTareoDiario(trabajador);
    }

    @Override
    public void guardarMarcacionInicioRefrigerio(List<TrabajadorTareoDiario> trabajadores, LocalDateTime fechaRefrigerio) {
        List<TareoObraEntity> asignaciones = trabajadores.stream()
                .map(trabajador -> {
                    TareoObraEntity tareoEntity = entityManager.getReference(TareoObraEntity.class, trabajador.getIdAsignacion());
                    tareoEntity.setFechaInicioReceso(fechaRefrigerio);
                    return tareoEntity;
                })
                .toList();

        tareoObraRepository.saveAll(asignaciones);

    }

    @Override
    public void guardarMarcacionFinRefrigerio(List<TrabajadorTareoDiario> trabajadores, LocalDateTime fechaRefrigerio) {
        List<TareoObraEntity> asignaciones = trabajadores.stream()
                .map(trabajador -> {
                    TareoObraEntity tareoEntity = entityManager.getReference(TareoObraEntity.class, trabajador.getIdAsignacion());
                    tareoEntity.setFechaFinReceso(fechaRefrigerio);
                    return tareoEntity;
                })
                .toList();

        tareoObraRepository.saveAll(asignaciones);

    }

    private TrabajadorAsignado parseEntityToTrabajadorAsignado(TareoObraEntity tareoObraEntity) {
        UsuarioEntity usuarioEntity = tareoObraEntity.getUsuario();
        return TrabajadorAsignado.builder()
                .idAsignacion(tareoObraEntity.getId())
                .idUsuario(usuarioEntity.getId())
                .cargo(usuarioEntity.getCargo().getCargo())
                .nombre(usuarioEntity.getNombre())
                .build();
    }

    private TrabajadorTareoDiario parseEntityToTrabajadorTareoDiario(TareoObraEntity tareoObraEntity) {
        UsuarioEntity usuarioEntity = tareoObraEntity.getUsuario();
        boolean calcularHorasExtras = tareoObraEntity.getFechaInicioHe() != null && tareoObraEntity.getFechaFinHe() != null;
        return TrabajadorTareoDiario.builder()
                .idAsignacion(tareoObraEntity.getId())
                .nombre(usuarioEntity.getNombre())
                .dni(usuarioEntity.getDni())
                .horaInicio(DateFormatter.localDateTimeToHourAmPm(tareoObraEntity.getFechaInicioDia()))
                .horaSalida(DateFormatter.localDateTimeToHourAmPm(tareoObraEntity.getFechaFinDia()))
                .horaInicioRefrigerio(DateFormatter.localDateTimeToHourAmPm(tareoObraEntity.getFechaInicioReceso()))
                .horaSalidaRefrigerio(DateFormatter.localDateTimeToHourAmPm(tareoObraEntity.getFechaFinReceso()))
                .horaExtraInicioDia(DateFormatter.localDateTimeToHourAmPm(tareoObraEntity.getFechaInicioHe()))
                .horaExtraSalidaDia(DateFormatter.localDateTimeToHourAmPm(tareoObraEntity.getFechaFinHe()))
                .diferenciaHorasExtras(calcularHorasExtras ? Duration.between(tareoObraEntity.getFechaInicioHe(),tareoObraEntity.getFechaFinHe()).toHours(): 0)
                .build();
    }

}
