package com.example.StatusObra.repositories.SolicitudPedidosRepository;

import com.example.StatusObra.persistence.solicitudpedidos.ProductoTop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository <ProductoTop,Long> {
}
