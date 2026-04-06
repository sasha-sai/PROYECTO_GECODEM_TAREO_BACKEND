package com.gecodem.tareo.application;

import com.gecodem.tareo.api.dto.BaseId;
import com.gecodem.tareo.api.dto.GenerarTareo;
import com.gecodem.tareo.domain.model.*;
import com.gecodem.tareo.domain.port.SupervisorObraPort;
import com.gecodem.tareo.domain.port.TareoObraPort;
import com.gecodem.tareo.utils.constantes.Respuestas;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TareoObraService {

    private final TareoObraPort tareoObraPort;
    private final SupervisorObraPort supervisorObraPort;

    @Transactional
    public Response guardarTareo(GenerarTareo generarTareo) {
        Response error = new Response(Respuestas.CODIGO_ERROR, Respuestas.MENSAJE_GUARDAR_ERROR);

        Set<Long> usuarios = new HashSet<>(generarTareo.getUsuarios());

        if (usuarios.size() < generarTareo.getUsuarios().size()) {
            error.setMensaje(Respuestas.MENSAJE_USUARIO_REPETIDO);
            return error;
        }

        List<Long> usuariosRegistrados = tareoObraPort.idUsuariosEnTareo(generarTareo.getIdAsignacionObra(), LocalDate.now().plusDays(1));

        boolean usuarioRepetido = usuariosRegistrados.stream().anyMatch(usuarios::contains);

        if(usuarioRepetido) {
            error.setMensaje(Respuestas.MENSAJE_USUARIO_REPETIDO);
            return error;
        }

        tareoObraPort.guardarTareoDiario(generarTareo.getIdAsignacionObra(), generarTareo.getUsuarios());
        return new Response(Respuestas.CODIGO_EXITO, Respuestas.MENSAJE_GUARDAR_EXITO);

    }

    public List<TrabajadorAsignado> listarTrabajadoresEnTareo(Long idObraAsignada) {
        return tareoObraPort.usuariosEnTareo(idObraAsignada, LocalDate.now().plusDays(1));
    }

    public Response eliminarUsuarioDeTareo(Long idUsuario) {
        tareoObraPort.eliminarUsuarioDelTareo(idUsuario);
        return new Response(Respuestas.CODIGO_EXITO, Respuestas.MENSAJE_ELIMINACION_EXITO);
    }

    public TareoDiario obtenerDetalleTareo(Long idUsuario) {
        AsignacionSupervisorObra detalleAsignacion = supervisorObraPort.obtenerDetalleAsignacionObra(idUsuario, LocalDate.now());
        List<TrabajadorTareoDiario> trabajadores = obtenerTrabajadoresTareoDiario(detalleAsignacion.getIdAsignacion());

        boolean inicioRefrigerio = trabajadores.stream().anyMatch(trabajador -> trabajador.getHoraInicioRefrigerio() != null);
        boolean finRefrigerio = trabajadores.stream().anyMatch(trabajador -> trabajador.getHoraSalidaRefrigerio() != null);

        return TareoDiario.builder()
                .nombreObra(detalleAsignacion.getNombreObra())
                .flgInicioRefrigerio(inicioRefrigerio ? 0 : 1)
                .flgFinRefrigerio(!inicioRefrigerio && !finRefrigerio ? 0 : 1)
                .flgFinDia(finRefrigerio ? 1 : 0)
                .flgReabrirDia(detalleAsignacion.getFlagCierreDia())
                .horaInicio(detalleAsignacion.getHorarioInicio())
                .horaFin(detalleAsignacion.getHorarioFin())
                .trabajadores(trabajadores)
                .build();
    }

    public TrabajadorTareoDiario marcarIngreso(BaseId baseId) {
        return tareoObraPort.marcarIngresoTrabajadorDeTareo(baseId.getId(), LocalDateTime.now());
    }

    @Transactional
    public EstadoHorarioTareoDiario marcarInicioRefrigerioEnTareo(BaseId baseId) {
        List<TrabajadorTareoDiario> trabajadores = obtenerTrabajadoresTareoDiario(baseId.getId())
                .stream().filter(tareo -> tareo.getHoraInicio() != null).toList();

        tareoObraPort.guardarMarcacionInicioRefrigerio(trabajadores, LocalDateTime.now());
        return EstadoHorarioTareoDiario.builder()
                .flgInicioRefrigerio(0)
                .flgFinRefrigerio(1)
                .flgReabrirDia(0)
                .flgFinDia(0)
                .build();
    }

    private List<TrabajadorTareoDiario> obtenerTrabajadoresTareoDiario(Long idObraAsignada) {
        return tareoObraPort.obtenerTrabajadoresDeTareo(idObraAsignada, LocalDate.now());
    }

}
