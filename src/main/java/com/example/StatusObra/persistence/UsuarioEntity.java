package com.example.StatusObra.persistence;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email // esta anotacion es para que si o si el campo se email
    @NotBlank // para que el campo no estee vacio
    @Size( max = 80)
    private String email;

    @NotBlank
    @Size( max = 30)
    private String username;

    @NotBlank
    private String dni;

    @NotBlank
    private String password;

    //usamos un Set porque no nos va a dejar que en el rol se repita admin
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST) //eager hace que traiga todos los roles asociados al usuario, si usamos lazy traeria 1 por 1
    @JoinTable( name = "cod_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")) // esta es la tabla intermedia que se generara
    private Set<RoleEntity> roles ;}





