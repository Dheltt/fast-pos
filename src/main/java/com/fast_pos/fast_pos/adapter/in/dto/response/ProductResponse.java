package com.fast_pos.fast_pos.adapter.in.dto.response;

import com.fast_pos.fast_pos.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ProductResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private int stock;
    private String category;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.category = product.getCategory();
    }
}
