package com.gecodem.tareo.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseId {
    @NotNull
    private Long id;
}
