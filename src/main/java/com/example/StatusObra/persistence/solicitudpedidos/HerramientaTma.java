package com.example.StatusObra.persistence.solicitudpedidos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "HERRAMIENTA_TMA")
public class HerramientaTma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long  cod_producto ;
    private String nombre;
    private Long num_serie;
    private String ubicacion;
    private Integer dias_restantes;
    private String cod_estado;

}
