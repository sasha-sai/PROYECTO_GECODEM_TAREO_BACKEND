package com.gecodem.tareo.service;


import com.gecodem.tareo.persistence.UsuarioEntity;

import java.util.Optional;

public interface UsuarioService {


    Optional<UsuarioEntity> getUsuarioById(Long id);
}