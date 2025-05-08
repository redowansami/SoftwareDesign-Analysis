package com.example.usermng.infrastructure.persistence;

import com.example.usermng.domain.User;
import com.example.usermng.domain.Role;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserJpaEntity toJpaEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setRoles(user.getRoles().stream().map(RoleMapper::toJpaEntity).collect(Collectors.toList()));
        return entity;
    }

    public static User toDomainEntity(UserJpaEntity entity) {
        User user = new User(entity.getId(), entity.getName(), entity.getEmail());
        entity.getRoles().forEach(roleJpa -> user.assignRole(RoleMapper.toDomainEntity(roleJpa)));
        return user;
    }
}