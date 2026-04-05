package com.gecodem.tareo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TareoDiario {

    private String nombreObra;
    private String horaInicio;
    private String horaFin;
    private Integer flgInicioRefrigerio;
    private Integer flgFinRefrigerio;
    private Integer flgFinDia;
    private Integer flgReabrirDia;

    private List<TrabajadorTareoDiario> trabajadores;

}
