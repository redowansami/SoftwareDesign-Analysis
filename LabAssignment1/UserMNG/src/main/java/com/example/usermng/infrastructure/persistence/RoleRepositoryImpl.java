package com.example.usermng.infrastructure.persistence;

import com.example.usermng.application.interfaces.RoleRepository;
import com.example.usermng.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryImpl(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Role save(Role role) {
        return RoleMapper.toDomainEntity(roleJpaRepository.save(RoleMapper.toJpaEntity(role)));
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleJpaRepository.findById(id).map(RoleMapper::toDomainEntity);
    }
}
