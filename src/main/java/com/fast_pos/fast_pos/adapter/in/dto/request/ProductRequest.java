package com.fast_pos.fast_pos.adapter.in.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private int stock;
    private String category;
}
