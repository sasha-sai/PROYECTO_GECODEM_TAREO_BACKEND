package com.gecodem.tareo.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GenerarTareo {
    @NotNull(message = "No ha asignado una obra")
    private Long idAsignacionObra;
    @NotEmpty(message = "Debe agregar usuarios")
    private List<Long> usuarios;
}
