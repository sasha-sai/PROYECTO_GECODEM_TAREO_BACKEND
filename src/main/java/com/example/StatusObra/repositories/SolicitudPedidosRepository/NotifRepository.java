package com.example.StatusObra.repositories.SolicitudPedidosRepository;

import com.example.StatusObra.persistence.solicitudpedidos.NotifTpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifRepository extends CrudRepository <NotifTpa, Long> {
}
