package com.example.StatusObra.dto.SolicitudPedidosDTO.form;

import lombok.Data;

@Data
public class FormHerramienta {
    private Long id;
    private Long  cod_producto ;
    private String nombre;
    private Long num_serie;
    private String ubicacion;
    private Integer dias_restantes;
    private String cod_estado;

}
