package com.example.usermng.infrastructure.persistence;

import com.example.usermng.domain.Role;

public class RoleMapper {
    public static RoleJpaEntity toJpaEntity(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        return entity;
    }

    public static Role toDomainEntity(RoleJpaEntity entity) {
        return new Role(entity.getId(), entity.getRoleName());
    }
}
