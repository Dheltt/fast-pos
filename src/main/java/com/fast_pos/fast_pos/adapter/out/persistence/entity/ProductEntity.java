package com.fast_pos.fast_pos.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    @Column(name = "stock",nullable = false)
    private int stock;
    private String category;
}
