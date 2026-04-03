package com.gecodem.tareo.infraestructure.adapter.impl;

import com.gecodem.tareo.domain.model.Trabajador;
import com.gecodem.tareo.domain.port.UsuarioPort;
import com.gecodem.tareo.infraestructure.persistence.UsuarioEntity;
import com.gecodem.tareo.infraestructure.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UsuarioAdapter implements UsuarioPort {
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Trabajador> getTrabajadores() {
        return usuarioRepository.findTrabajadores()
                .stream()
                .map(this::entityToModel)
                .toList();
    }

    private Trabajador entityToModel(UsuarioEntity usuarioEntity) {
        return Trabajador.builder()
                .id(usuarioEntity.getId())
                .nombre(usuarioEntity.getNombre())
                .cargo(usuarioEntity.getCargo().getCargo())
                .build();
    }
}
