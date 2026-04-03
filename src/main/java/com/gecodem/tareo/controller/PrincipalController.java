package com.gecodem.tareo.controller;

import com.gecodem.tareo.dto.CreateUserDTO;
import com.gecodem.tareo.persistence.RoleEntity;
import com.gecodem.tareo.persistence.UsuarioEntity;
import com.gecodem.tareo.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class PrincipalController {
    private PasswordEncoder passwordEncoder;

    private UsuarioRepository userRepository;

    @PostMapping("/createUser")
    public ResponseEntity<UsuarioEntity> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {


        // Construir el usuario con el campo dni
        UsuarioEntity userEntity = UsuarioEntity.builder()
                .nombre(createUserDTO.getUsername())
                .dni(createUserDTO.getDni()) // Agregado dni
                .contrasena(passwordEncoder.encode(createUserDTO.getPassword()))
                .role(RoleEntity.builder().id(1L).build())
                .email(createUserDTO.getEmail())
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

}
