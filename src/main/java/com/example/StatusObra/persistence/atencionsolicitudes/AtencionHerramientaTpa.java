package com.example.StatusObra.persistence.atencionsolicitudes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "AT_SOL_TPA")



public class AtencionHerramientaTpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cod_solicitud;
    private Long cod_usuario_atencion ;
    private Date fec_hora_envio;
    private Date fec_hora_recepcion ;
    private String recibido_por;
    private Date fec_fin_sol;
    private Long dias_sol;
    private String  ampliaciones;

}
