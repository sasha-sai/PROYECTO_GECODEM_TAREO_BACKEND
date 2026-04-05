package com.gecodem.tareo.infraestructure.persistence;
import com.gecodem.tareo.utils.constantes.Tablas;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Tablas.SUPERVISOR_OBRA)
public class SupervisorObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sup_obra")
    private Long id;

    @NotNull // para que el campo no estee vacio
    @Size( max = 80)
    private LocalDate fecha;

    @Column(name = "flg_cierre_dia")
    private Integer flgCierreDia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obra", nullable = false)
    private ObraEntity obra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

}





