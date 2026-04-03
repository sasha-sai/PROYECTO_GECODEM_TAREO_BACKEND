package com.gecodem.tareo.infraestructure.repositories;

import com.gecodem.tareo.infraestructure.persistence.SupervisorObraEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupervisorObraRepository extends CrudRepository<SupervisorObraEntity, Long> {
    List<SupervisorObraEntity> findByUsuarioId(Long idUsuario);
}
