package com.example.usermng.application;

import com.example.usermng.domain.Role;
import com.example.usermng.application.interfaces.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void testCreateRole() {
        UUID roleId = UUID.randomUUID();
        Role role = new Role(roleId, "ADMIN");
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        UUID resultId = roleService.createRole("ADMIN");

        assertEquals(roleId, resultId);
        verify(roleRepository).save(any(Role.class));
    }
}