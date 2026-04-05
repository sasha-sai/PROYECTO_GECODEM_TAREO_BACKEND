package com.gecodem.tareo.application;

import com.gecodem.tareo.domain.model.AsignacionSupervisorObra;
import com.gecodem.tareo.domain.port.SupervisorObraPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AsignacionSupervisorObraService {
    private final SupervisorObraPort supervisorObraPort;

    public List<AsignacionSupervisorObra> obtenerAsignacionesDeObra(Long idSupervisor) {
        return supervisorObraPort.obtenerAsignacionObrasPorSupervisor(idSupervisor);
    }

    public AsignacionSupervisorObra obtenerAsignacionDeObraConTareo(Long idUsuario) {
        try {
            return supervisorObraPort.obtenerDetalleAsignacionObra(idUsuario, LocalDate.now().plusDays(1));
        } catch (Exception ex) {
            return AsignacionSupervisorObra.builder().build();
        }
    }
}
