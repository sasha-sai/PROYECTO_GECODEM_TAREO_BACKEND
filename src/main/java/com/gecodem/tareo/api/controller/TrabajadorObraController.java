package com.gecodem.tareo.api.controller;

import com.gecodem.tareo.application.UsuarioObraService;
import com.gecodem.tareo.domain.model.Trabajador;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trabajador")
@AllArgsConstructor
public class TrabajadorObraController {

    private final UsuarioObraService service;

    @GetMapping("/lista")
    public ResponseEntity<List<Trabajador>> obtenerAsignacionesDeObra() {
        return ResponseEntity.ok(service.obtenerTrabajadores());
    }

}
