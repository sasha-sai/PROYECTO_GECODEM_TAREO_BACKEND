package com.gecodem.tareo.infraestructure.repositories;

import com.gecodem.tareo.infraestructure.persistence.SupervisorObraEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupervisorObraRepository extends CrudRepository<SupervisorObraEntity, Long> {
    List<SupervisorObraEntity> findByUsuarioId(Long idUsuario);

    Optional<SupervisorObraEntity> findByUsuarioIdAndFecha(Long idUsuario, LocalDate fecha);

}
