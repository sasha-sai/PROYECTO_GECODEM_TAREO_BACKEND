package com.example.StatusObra.dto.SolicitudPedidosDTO.form;

import com.example.StatusObra.persistence.UsuarioEntity;
import lombok.Data;

import java.util.Date;

@Data

public class SolicitudHerramientaDTO {


    private Long id;
    private UsuarioEntity usuario;
    private Date fecha_de_solicitud;
    private Long dias_solicitados;

}

