package com.fast_pos.fast_pos.adapter.in.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserRegistrationRequest {
    private String name;
    private String storeName;
    private String email;
    private String password;
    private MultipartFile productsCsv;
}
