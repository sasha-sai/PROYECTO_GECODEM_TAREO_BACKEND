package com.gecodem.tareo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EstadoHorarioTareoDiario {

    private Integer flgInicioRefrigerio;
    private Integer flgFinRefrigerio;
    private Integer flgFinDia;
    private Integer flgReabrirDia;

}
