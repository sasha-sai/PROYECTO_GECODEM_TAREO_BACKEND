package com.gecodem.tareo.api.controller;

import com.gecodem.tareo.application.AsignacionSupervisorObraService;
import com.gecodem.tareo.domain.model.AsignacionSupervisorObra;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supervisor-obra")
@AllArgsConstructor
public class AsignacionSupervisorObraController {

    private final AsignacionSupervisorObraService service;

    @GetMapping("/asignacion")
    public ResponseEntity<List<AsignacionSupervisorObra>> obtenerAsignacionesDeObra(@RequestParam Long idSupervisor) {
        return ResponseEntity.ok(service.obtenerAsignacionesDeObra(idSupervisor));
    }

    @GetMapping("/tareo")
    public ResponseEntity<AsignacionSupervisorObra> obtenerTareoEnAsignacionObra(@RequestParam Long idUsuario) {
        return ResponseEntity.ok(service.obtenerAsignacionDeObraConTareo(idUsuario));
    }

}
