package com.gecodem.tareo.infraestructure.adapter.impl;



import com.gecodem.tareo.infraestructure.persistence.UsuarioEntity;
import com.gecodem.tareo.infraestructure.repositories.UsuarioRepository;
import com.gecodem.tareo.infraestructure.adapter.UsuarioService;
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
