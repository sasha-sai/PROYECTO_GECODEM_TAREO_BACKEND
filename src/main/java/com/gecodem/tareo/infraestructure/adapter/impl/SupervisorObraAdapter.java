package com.gecodem.tareo.infraestructure.adapter.impl;

import com.gecodem.tareo.domain.model.AsignacionSupervisorObra;
import com.gecodem.tareo.domain.port.SupervisorObraPort;
import com.gecodem.tareo.infraestructure.persistence.ObraEntity;
import com.gecodem.tareo.infraestructure.persistence.SupervisorObraEntity;
import com.gecodem.tareo.infraestructure.repositories.SupervisorObraRepository;
import com.gecodem.tareo.utils.DateFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class SupervisorObraAdapter implements SupervisorObraPort {
    private final SupervisorObraRepository supervisorObraRepository;

    @Override
    public List<AsignacionSupervisorObra> obtenerAsignacionObrasPorSupervisor(Long idSupervisor) {
        return supervisorObraRepository.findByUsuarioId(idSupervisor)
                .stream()
                .map(this::mapEntityToModel)
                .toList();
    }

    @Override
    public AsignacionSupervisorObra obtenerDetalleAsignacionObra(Long idAsignacionObra) {
        return supervisorObraRepository.findById(idAsignacionObra)
                .map(this::mapEntityToModel)
                .orElseThrow();
    }

    private AsignacionSupervisorObra mapEntityToModel(SupervisorObraEntity entity) {
        ObraEntity  obraEntity = entity.getObra();
        return AsignacionSupervisorObra
                .builder()
                .nombreObra(obraEntity.getObra())
                .ceco(obraEntity.getCeco())
                .fechaAsignacion(DateFormatter.dateToString(entity.getFecha()))
                .idAsignacion(entity.getId())
                .flagCierreDia(entity.getFlgCierreDia())
                .horarioInicio(DateFormatter.localDateTimeToHourAmPm(obraEntity.getHorarioInicio()))
                .horarioFin(DateFormatter.localDateTimeToHourAmPm(obraEntity.getHorarioFin()))
                .ubicacionObra(obraEntity.getDireccion())
                .build();
    }
}
