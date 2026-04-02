package com.example.StatusObra.persistence.recepcionpedido;

import com.example.StatusObra.persistence.RoleEntity;
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
@Table(name = "NOTIF_TPA")
public class NotifTpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //pendiente forey key//
    @ManyToOne
    @JoinColumn(name = "cod_usuario_origen")
    private UsuarioEntity usuarioOrigen;

    @ManyToOne
    @JoinColumn(name = "cod_usuario_destino")
    private UsuarioEntity usuarioDestino;

    @ManyToOne
    @JoinColumn(name = "cod_rol")
    private RoleEntity rol;

    private Date fecha_envio;
    private String leido ;

}
