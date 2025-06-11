package com.fast_pos.fast_pos.adapter.in.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponse {
    private String token;
    private String tokenType = "Bearer";
    public JwtResponse(String token) {
        this.token = token;
    }
}
