package com.gecodem.tareo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response {
    // Ok: 1, Error: 0
    private Integer codigo;
    private String mensaje;
}
