package com.gecodem.tareo.domain.port;

import com.gecodem.tareo.domain.model.Trabajador;

import java.util.List;

public interface UsuarioPort {

    List<Trabajador> getTrabajadores();
}
