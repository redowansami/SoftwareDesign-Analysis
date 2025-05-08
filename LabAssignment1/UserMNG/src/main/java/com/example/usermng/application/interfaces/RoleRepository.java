package com.example.usermng.application.interfaces;

import com.example.usermng.domain.Role;
import java.util.*;

public interface RoleRepository {
    Role save(Role role);
    Optional<Role> findById(UUID id);
}
