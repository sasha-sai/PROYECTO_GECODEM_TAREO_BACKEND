package com.gecodem.tareo.service.impl;

import com.gecodem.tareo.model.UsuarioLogueado;
import com.gecodem.tareo.persistence.UsuarioEntity;
import com.gecodem.tareo.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity userEntity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe."));

        return new UsuarioLogueado(userEntity.getId(),userEntity.getEmail(), userEntity.getNombre(), userEntity.getContrasena(), userEntity.getRole().getRol(), null);
    }
}
