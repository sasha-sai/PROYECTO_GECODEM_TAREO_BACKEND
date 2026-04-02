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
@Table(name = "SOL_PED_TPA")
public class SolicitudHerramientaTpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long cod_usuario;
  private Date fec_de_sol;
  private Integer  dias_solicitados;

}
