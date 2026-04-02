package com.example.StatusObra.service.impl;



import com.example.StatusObra.persistence.UsuarioEntity;
import com.example.StatusObra.repositories.UsuarioRepository;
import com.example.StatusObra.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioEntity> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
}
