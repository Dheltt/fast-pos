package com.fast_pos.fast_pos.adapter.in.dto.response;

import com.fast_pos.fast_pos.domain.enums.UserRole;
import com.fast_pos.fast_pos.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String tenantStoreName;
    private String name;
    private String email;
    private UserRole role;

    public UserResponse(User user){
        this.id=user.getId();
        this.name= user.getName();
        this.email=user.getEmail();
        this.role=user.getRole();
    }
}
