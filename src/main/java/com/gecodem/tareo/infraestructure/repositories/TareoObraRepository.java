package com.gecodem.tareo.infraestructure.repositories;

import com.gecodem.tareo.infraestructure.persistence.TareoObraEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TareoObraRepository extends CrudRepository<TareoObraEntity, Long> {

    @Query("""
        SELECT t 
        FROM TareoObraEntity t 
        JOIN t.asignacionSupervisorObra a 
        WHERE a.id = :idSupervisorObra 
        AND t.fechaAsignacion = :fecha
    """)
    List<TareoObraEntity> findUsuariosConTareo(
            @Param("idSupervisorObra") Long idSupervisorObra,
            @Param("fecha") LocalDate fecha);
}
