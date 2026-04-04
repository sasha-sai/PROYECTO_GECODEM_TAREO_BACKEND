package com.gecodem.tareo.infraestructure.persistence;
import com.gecodem.tareo.utils.constantes.Tablas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Tablas.TAREO_OBRA)
public class TareoObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tareo_obra")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sup_obra", nullable = false)
    private SupervisorObraEntity asignacionSupervisorObra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    @Column(name = "fecha_inicio_dia")
    private LocalDateTime fechaInicioDia;

    @Column(name = "fecha_fin_dia")
    private LocalDateTime fechaFinDia;

    @Column(name = "fecha_inicio_receso")
    private LocalDateTime fechaInicioReceso;

    @Column(name = "fecha_fin_receso")
    private LocalDateTime fechaFinReceso;

    @Column(name = "fecha_inicio_he")
    private LocalDateTime fechaInicioHe;

    @Column(name = "fecha_fin_he")
    private LocalDateTime fechaFinHe;

    @Column(name = "flg_compensacion")
    private Integer flagCompensacion;

    private String comentario;

}





