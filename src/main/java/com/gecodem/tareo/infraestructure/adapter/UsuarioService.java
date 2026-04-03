package com.gecodem.tareo.infraestructure.adapter;


import com.gecodem.tareo.infraestructure.persistence.UsuarioEntity;

import java.util.Optional;

public interface UsuarioService {


    Optional<UsuarioEntity> getUsuarioById(Long id);
}