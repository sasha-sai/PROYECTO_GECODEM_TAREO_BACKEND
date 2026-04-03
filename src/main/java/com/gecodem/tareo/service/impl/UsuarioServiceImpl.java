package com.gecodem.tareo.service.impl;



import com.gecodem.tareo.persistence.UsuarioEntity;
import com.gecodem.tareo.repositories.UsuarioRepository;
import com.gecodem.tareo.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioEntity> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
}
