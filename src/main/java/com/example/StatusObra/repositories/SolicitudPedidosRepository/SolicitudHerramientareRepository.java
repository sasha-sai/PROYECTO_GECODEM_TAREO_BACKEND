package com.example.StatusObra.repositories.SolicitudPedidosRepository;

import com.example.StatusObra.persistence.solicitudpedidos.SolicitudHerramientaTpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudHerramientareRepository extends CrudRepository <SolicitudHerramientaTpa,Long> {
}
