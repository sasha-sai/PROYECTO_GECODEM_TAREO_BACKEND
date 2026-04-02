package com.example.StatusObra.repositories.SolicitudPedidosRepository;

import com.example.StatusObra.persistence.solicitudpedidos.EstadoHerramientaTma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoHerramientsRepository extends CrudRepository <EstadoHerramientaTma,Long> {
}
