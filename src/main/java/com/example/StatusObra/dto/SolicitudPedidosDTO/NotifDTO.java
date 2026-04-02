package com.example.StatusObra.dto.SolicitudPedidosDTO;

import lombok.Data;

import java.util.Date;


@Data
public class NotifDTO {

    private Long id;
    private Long cod_usuario_origen;
    private Long cod_usuario_destino;
    private Long cod_rol;
    private Date fecha_envio;
    private String leido ;

}
