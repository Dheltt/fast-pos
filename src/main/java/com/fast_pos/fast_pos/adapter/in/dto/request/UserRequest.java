package com.fast_pos.fast_pos.adapter.in.dto.request;

import com.fast_pos.fast_pos.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserRequest {
    @NotBlank
    private String name;
    private String tenantStoreName;
    @Email(message = "El email no tiene un formato valido")
    private String email;
    @NotBlank
    private String password;
    @NotNull(message = "Role cannot be null")
    private UserRole role;
}
