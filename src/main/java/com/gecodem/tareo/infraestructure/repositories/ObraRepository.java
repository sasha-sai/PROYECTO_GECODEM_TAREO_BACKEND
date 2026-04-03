package com.gecodem.tareo.infraestructure.repositories;

import com.gecodem.tareo.infraestructure.persistence.ObraEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends CrudRepository<ObraEntity, Long> {
}
