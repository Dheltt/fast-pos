package com.fast_pos.fast_pos.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RegisterStoreRequest {
    @Schema(description = "Owner's full name", example = "papu")
    private String ownerName;

    @Schema(description = "Email address", example = "papu@gmail.com")
    private String email;

    @Schema(description = "Password", example = "12345678")
    private String password;

    @Schema(description = "Store name", example = "papusss")
    private String storeName;
}
