package com.example.usermng.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class RoleJpaEntity {
    @Id
    private UUID id;
    private String roleName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}