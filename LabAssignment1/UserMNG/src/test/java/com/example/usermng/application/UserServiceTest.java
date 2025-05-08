package com.example.usermng.application;

import com.example.usermng.application.interfaces.RoleRepository;
import com.example.usermng.domain.Role;
import com.example.usermng.domain.User;
import com.example.usermng.application.interfaces.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "test", "email@test.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        UUID resultId = userService.createUser("test", "email@test.com");

        assertEquals(userId, resultId);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_shouldSaveUserAndReturnId() {
        String name = "John Doe";
        String email = "john@example.com";

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UUID userId = userService.createUser(name, email);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(userId);
    }

    @Test
    void assignRoleToUser_shouldAssignRoleAndSaveUser() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        User user = new User(userId, "John Doe", "john@example.com");
        Role role = new Role(roleId, "ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        userService.assignRoleToUser(userId, roleId);

        verify(userRepository).save(user);
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    void assignRoleToUser_shouldThrowExceptionIfUserNotFound() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userService.assignRoleToUser(userId, roleId));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void assignRoleToUser_shouldThrowExceptionIfRoleNotFound() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        User user = new User(userId, "Jane Doe", "jane@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userService.assignRoleToUser(userId, roleId));

        assertEquals("Role not found", exception.getMessage());
    }

    @Test
    void getUserDetails_shouldReturnUser() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "John Doe", "john@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUser(userId);

        assertEquals(user, result);
    }

    @Test
    void getUserDetails_shouldThrowExceptionIfUserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userService.getUser(userId));

        assertEquals("User not found", exception.getMessage());
    }
}
