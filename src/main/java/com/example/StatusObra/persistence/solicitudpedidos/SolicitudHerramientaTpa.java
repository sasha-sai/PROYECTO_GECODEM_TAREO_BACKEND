package com.example.StatusObra.persistence.solicitudpedidos;

import com.example.StatusObra.persistence.UsuarioEntity;
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

    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    private UsuarioEntity usuario;

    private Date fecha_de_solicitud;
    private Long dias_solicitados;

}

