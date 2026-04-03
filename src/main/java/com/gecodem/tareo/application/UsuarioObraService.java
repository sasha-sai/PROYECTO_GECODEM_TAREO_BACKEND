package com.gecodem.tareo.application;

import com.gecodem.tareo.domain.model.Trabajador;
import com.gecodem.tareo.domain.port.UsuarioPort;
import com.gecodem.tareo.infraestructure.adapter.impl.UsuarioAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioObraService {

    private final UsuarioPort usuarioPort;

    public List<Trabajador> obtenerTrabajadores() {
        return usuarioPort.getTrabajadores();
    }
}
