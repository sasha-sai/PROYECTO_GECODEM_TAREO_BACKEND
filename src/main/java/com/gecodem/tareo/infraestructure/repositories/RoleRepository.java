package com.gecodem.tareo.infraestructure.repositories;

import com.gecodem.tareo.infraestructure.persistence.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
}
