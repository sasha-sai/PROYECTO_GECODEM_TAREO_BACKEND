package com.example.StatusObra.service.solicitudpedidos;

import com.example.StatusObra.dto.SolicitudPedidosDTO.form.FormBuscarHerramienta;
import com.example.StatusObra.dto.SolicitudPedidosDTO.form.FormCambiarEstado;
import com.example.StatusObra.dto.SolicitudPedidosDTO.form.FormHerramienta;
import com.example.StatusObra.dto.SolicitudPedidosDTO.model.ModelEstadoHerramienta;
import com.example.StatusObra.dto.SolicitudPedidosDTO.model.ModelRetornarProducto;
import org.springframework.data.domain.Page;

public interface HerramientaService {

    Page<ModelEstadoHerramienta> listarHerramientas (FormBuscarHerramienta formBuscarHerramienta);
    void cambiarEstadoHerramienta (FormCambiarEstado formCambiarEstado);

    void registrarHerramienta (FormHerramienta formHerramienta);
    void eliminarHerramienta (Long id);
    ModelRetornarProducto retornarProducto ();
}
