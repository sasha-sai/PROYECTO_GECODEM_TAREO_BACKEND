package com.gecodem.tareo.infraestructure.repositories;

import com.gecodem.tareo.infraestructure.persistence.CargoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<CargoEntity, Long> {
}
