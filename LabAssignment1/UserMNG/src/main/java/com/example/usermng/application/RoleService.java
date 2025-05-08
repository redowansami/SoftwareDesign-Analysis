package com.example.usermng.application;

import com.example.usermng.application.interfaces.RoleRepository;
import com.example.usermng.domain.Role;
import java.util.*;

public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UUID createRole(String roleName) {
        Role role = new Role(UUID.randomUUID(), roleName);
        return roleRepository.save(role).getId();
    }
}
