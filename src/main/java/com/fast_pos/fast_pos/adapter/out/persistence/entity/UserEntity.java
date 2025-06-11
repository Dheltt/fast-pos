package com.fast_pos.fast_pos.adapter.out.persistence.entity;

import com.fast_pos.fast_pos.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users") // No uses schema aquí, lo gestiona Hibernate con setSchema()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id @GeneratedValue
    private UUID id;
    @Column(name="store_name")
    private String tenantStoreName;
    @Column(name="name")
    private String username;

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
