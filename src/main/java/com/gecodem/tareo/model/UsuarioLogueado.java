package com.gecodem.tareo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class UsuarioLogueado implements UserDetails {
    private final Long id;
    private final String email;
    private final String nombre;
    private final String contrasena;
    private final String rol;

    private final Collection<? extends GrantedAuthority> authorities;


    @Override
    public String getUsername() {
        return nombre; // Retornar el nombre de usuario, no el correo
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

