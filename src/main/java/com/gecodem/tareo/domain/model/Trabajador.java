package com.gecodem.tareo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Trabajador {
    private Long id;
    private String nombre;
    private String cargo;
}
