package com.fast_pos.fast_pos.adapter.in.dto.response;

import com.fast_pos.fast_pos.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class UserRegistrationResponse {
    private UserResponse user;
    private List<ProductResponse> products;
    private String errorMessage; // nuevo campo

    public UserRegistrationResponse(UserResponse user, List<ProductResponse> products) {
        this.user = user;
        this.products = products;
        this.errorMessage = null;
    }

    // ✅ Constructor para casos con error
    public UserRegistrationResponse(String errorMessage, List<Product> products) {
        this.user = null;
        this.products = products.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
        this.errorMessage = errorMessage;
    }
}
