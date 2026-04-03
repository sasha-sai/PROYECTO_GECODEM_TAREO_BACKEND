package com.gecodem.tareo.infraestructure.persistence;


import com.gecodem.tareo.utils.constantes.Tablas;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Tablas.OBRA)
public class ObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Long id;

    @NotBlank
    @Size( max = 200)
    private String obra;

    @NotBlank
    @Size( max = 50)
    private String ceco;

    @NotBlank
    @Size( max = 255)
    private String direccion;
}
