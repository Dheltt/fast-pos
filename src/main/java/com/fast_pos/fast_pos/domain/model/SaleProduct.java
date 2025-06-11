package com.fast_pos.fast_pos.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class SaleProduct {
    private UUID productId; // Id del producto
    private String productName; // Nombre del producto
    private int quantity; // Cantidad vendida
    private BigDecimal price; // Precio del producto

    // Constructor, getters y setters
    public SaleProduct(UUID productId, String productName,BigDecimal price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
