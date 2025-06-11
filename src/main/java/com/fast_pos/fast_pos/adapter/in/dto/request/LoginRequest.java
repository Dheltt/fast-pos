package com.fast_pos.fast_pos.adapter.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
