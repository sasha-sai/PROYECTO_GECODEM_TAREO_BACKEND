package com.gecodem.tareo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TrabajadorTareoDiario {

    private Long idAsignacion;
    private String nombre;
    private String dni;
    private String horaInicio;
    private String horaSalida;
    private String horaInicioRefrigerio;
    private String horaSalidaRefrigerio;
    private String horaExtraInicioDia;
    private String horaExtraSalidaDia;
    private String comentario;
    private Long diferenciaHorasExtras;

}
