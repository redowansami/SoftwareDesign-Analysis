package com.example.usermng.config;

import com.example.usermng.domain.Role;
import com.example.usermng.domain.User;
import org.springframework.context.annotation.*;
import com.example.usermng.application.*;
import com.example.usermng.application.interfaces.*;

import java.util.Optional;
import java.util.UUID;
@Configuration
public class BeanConfig {

    @Bean
    public UserService userService(UserRepository userRepository, RoleRepository roleRepository) {
        return new UserService(userRepository, roleRepository);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }
}
