package com.example.StatusObra.repositories.SolicitudPedidosRepository;

import com.example.StatusObra.persistence.solicitudpedidos.HerramientaTma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerramientaRepository extends CrudRepository <HerramientaTma, Long> {
}
