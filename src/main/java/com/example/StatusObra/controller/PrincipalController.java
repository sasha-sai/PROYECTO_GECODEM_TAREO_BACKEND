package com.example.StatusObra.controller;

import com.example.StatusObra.dto.CreateUserDTO;
import com.example.StatusObra.persistence.ERole;
import com.example.StatusObra.persistence.RoleEntity;
import com.example.StatusObra.persistence.UsuarioEntity;
import com.example.StatusObra.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {

        // Obtener los roles
        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        // Construir el usuario con el campo dni
        UsuarioEntity userEntity = UsuarioEntity.builder()
                .username(createUserDTO.getUsername())
                .dni(createUserDTO.getDni()) // Agregado dni
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id) {
        userRepository.deleteById(Long.parseLong(id));
        return "Se ha eliminado el usuario: ".concat(id);
    }
}
