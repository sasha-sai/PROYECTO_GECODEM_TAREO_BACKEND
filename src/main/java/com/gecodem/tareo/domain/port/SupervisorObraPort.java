package com.gecodem.tareo.domain.port;

import com.gecodem.tareo.domain.model.AsignacionSupervisorObra;

import java.util.List;

public interface SupervisorObraPort {

    List<AsignacionSupervisorObra> obtenerAsignacionObrasPorSupervisor(Long idSupervisor);

    AsignacionSupervisorObra obtenerDetalleAsignacionObra(Long idAsignacionObra);
}
