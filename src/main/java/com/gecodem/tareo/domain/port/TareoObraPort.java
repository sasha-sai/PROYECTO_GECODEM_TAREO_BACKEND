package com.gecodem.tareo.domain.port;

import com.gecodem.tareo.domain.model.TrabajadorAsignado;
import com.gecodem.tareo.domain.model.TrabajadorTareoDiario;

import java.time.LocalDate;
import java.util.List;

public interface TareoObraPort {

    void guardarTareoDiario(Long idObraAsignada, List<Long> usuarios);

    List<Long> idUsuariosEnTareo(Long idObraAsignada, LocalDate fechaAsignado);

    List<TrabajadorAsignado> usuariosEnTareo(Long idObraAsignada, LocalDate fechaAsignado);

    void eliminarUsuarioDelTareo(Long idUsuario);

    List<TrabajadorTareoDiario> obtenerTrabajadoresDeTareo(Long idObraAsignada, LocalDate fechaAsignado);
}
