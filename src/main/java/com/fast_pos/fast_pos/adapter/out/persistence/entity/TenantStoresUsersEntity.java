package com.fast_pos.fast_pos.adapter.out.persistence.entity;

import com.fast_pos.fast_pos.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity @Table(name="tenants_users", schema = "public") @Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class TenantStoresUsersEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(name = "schema_name")
    private String schemaName;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")  // o user_role si es diferente en DB
    private UserRole userRole;
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
