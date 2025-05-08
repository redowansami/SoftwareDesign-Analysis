package com.example.usermng.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private com.example.usermng.application.UserService userService;

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody UserDTO request) {
        UUID id = userService.createUser(request.name(), request.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<String> assignRole(
            @PathVariable UUID userId, @PathVariable UUID roleId) {
        userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role assigned");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    public record UserDTO(String name, String email) {}
}
