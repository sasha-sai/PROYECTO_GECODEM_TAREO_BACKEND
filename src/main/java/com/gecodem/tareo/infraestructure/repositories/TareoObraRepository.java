package com.gecodem.tareo.infraestructure.repositories;

import com.gecodem.tareo.infraestructure.persistence.TareoObraEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareoObraRepository extends CrudRepository<TareoObraEntity, Long> {
}
