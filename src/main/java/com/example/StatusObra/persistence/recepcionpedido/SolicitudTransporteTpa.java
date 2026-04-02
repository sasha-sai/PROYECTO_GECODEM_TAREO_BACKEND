package com.example.StatusObra.persistence.recepcionpedido;

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
@Table(name = "SOLICITUD_TRANSPORTE_TPA")
public class SolicitudTransporteTpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cod_atencion_sol;
    private Long  cod_transportista;
    private Long cod_estado;

}
