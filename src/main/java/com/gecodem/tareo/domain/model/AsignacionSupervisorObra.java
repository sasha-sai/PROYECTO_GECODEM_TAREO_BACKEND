package com.gecodem.tareo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AsignacionSupervisorObra {
    private Long idAsignacion;
    private String nombreObra;
    private String ceco;
    private String ubicacionObra;
    private String fechaAsignacion;

}
