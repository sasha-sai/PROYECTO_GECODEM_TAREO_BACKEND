package com.gecodem.tareo.infraestructure.persistence;
import com.gecodem.tareo.utils.constantes.Tablas;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = Tablas.USUARIO)
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Email // esta anotacion es para que si o si el campo se email
    @NotBlank // para que el campo no estee vacio
    @Size( max = 80)
    private String email;

    @NotBlank
    @Size( max = 20)
    private String celular;

    @NotBlank
    @Size( max = 300)
    private String nombre;

    @NotBlank
    private String dni;

    @NotBlank
    private String contrasena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo", nullable = false)
    private CargoEntity cargo;

}





