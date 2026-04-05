package com.gecodem.tareo.api.controller;

import com.gecodem.tareo.api.dto.BaseId;
import com.gecodem.tareo.api.dto.GenerarTareo;
import com.gecodem.tareo.application.TareoObraService;
import com.gecodem.tareo.domain.model.Response;
import com.gecodem.tareo.domain.model.TareoDiario;
import com.gecodem.tareo.domain.model.TrabajadorAsignado;
import com.gecodem.tareo.domain.model.TrabajadorTareoDiario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareo")
@AllArgsConstructor
public class TareoObraController {

    private final TareoObraService service;

    @PostMapping("/guardar")
    public ResponseEntity<Response> obtenerAsignacionesDeObra(@Valid @RequestBody GenerarTareo generarTareo) {
        return ResponseEntity.ok(service.guardarTareo(generarTareo));
    }

    @GetMapping("/trabajadores")
    public ResponseEntity<List<TrabajadorAsignado>> obtenerTrabajadoresDeTareo(@RequestParam Long idObraAsignada) {
        return ResponseEntity.ok(service.listarTrabajadoresEnTareo(idObraAsignada));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Response> eliminarTrabajadorEnTareo(@RequestParam Long idUsuarioAsignado) {
        return ResponseEntity.ok(service.eliminarUsuarioDeTareo(idUsuarioAsignado));
    }

    @GetMapping("/detalle")
    public ResponseEntity<TareoDiario> obtenerDetalleTareoDiario(@RequestParam Long idObraAsignada) {
        return ResponseEntity.ok(service.obtenerDetalleTareo(idObraAsignada));
    }

    @PostMapping("/ingreso")
    public ResponseEntity<TrabajadorTareoDiario> marcarIngresoDeTrabajador(@Valid @RequestBody BaseId id) {
        return ResponseEntity.ok(service.marcarIngreso(id));
    }

}
