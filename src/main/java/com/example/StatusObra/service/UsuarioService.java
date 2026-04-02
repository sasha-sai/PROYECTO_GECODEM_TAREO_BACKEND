package com.example.StatusObra.service;


import com.example.StatusObra.persistence.UsuarioEntity;

import java.util.Optional;

public interface UsuarioService {


    Optional<UsuarioEntity> getUsuarioById(Long id);
}