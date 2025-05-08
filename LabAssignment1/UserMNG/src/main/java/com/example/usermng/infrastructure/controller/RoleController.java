package com.example.usermng.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private com.example.usermng.application.RoleService roleService;

    @PostMapping
    public ResponseEntity<UUID> createRole(@RequestBody RoleDTO request) {
        UUID id = roleService.createRole(request.roleName());
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    public record RoleDTO(String roleName) {}
}