package com.gecodem.tareo.infraestructure.adapter.impl;

import com.gecodem.tareo.domain.model.TrabajadorAsignado;
import com.gecodem.tareo.domain.port.TareoObraPort;
import com.gecodem.tareo.infraestructure.persistence.SupervisorObraEntity;
import com.gecodem.tareo.infraestructure.persistence.TareoObraEntity;
import com.gecodem.tareo.infraestructure.persistence.UsuarioEntity;
import com.gecodem.tareo.infraestructure.repositories.TareoObraRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
                .stream().map(this::parseEntityToModel).toList();
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

    private TrabajadorAsignado parseEntityToModel(TareoObraEntity tareoObraEntity) {
        UsuarioEntity usuarioEntity = tareoObraEntity.getUsuario();
        return TrabajadorAsignado.builder()
                .idAsignacion(tareoObraEntity.getId())
                .idUsuario(usuarioEntity.getId())
                .cargo(usuarioEntity.getCargo().getCargo())
                .nombre(usuarioEntity.getNombre())
                .build();
    }

}
