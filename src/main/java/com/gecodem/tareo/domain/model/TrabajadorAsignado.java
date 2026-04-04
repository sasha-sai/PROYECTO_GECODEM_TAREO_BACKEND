package com.gecodem.tareo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TrabajadorAsignado {
    private Long idAsignacion;
    private Long idUsuario;
    private String nombre;
    private String cargo;
}
