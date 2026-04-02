package com.gecodem.tareo.repositories;

import com.gecodem.tareo.persistence.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);
}
