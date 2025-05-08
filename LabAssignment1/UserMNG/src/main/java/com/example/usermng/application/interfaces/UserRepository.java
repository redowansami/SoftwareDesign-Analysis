package com.example.usermng.application.interfaces;

import com.example.usermng.domain.User;
import java.util.*;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
}