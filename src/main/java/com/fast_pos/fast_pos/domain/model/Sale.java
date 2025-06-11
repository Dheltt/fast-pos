package com.fast_pos.fast_pos.domain.model;

import com.fast_pos.fast_pos.domain.enums.PayMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Sale {
    private UUID id;
    private UUID userId;  // Relacionado con el usuario que realizó la venta
    private PayMethod paymentMethod;
    private List<SaleProduct> products; // Productos vendidos (con cantidad y precio)
    private BigDecimal totalAmount; // El total de la venta
    private LocalDateTime timestamp; // Fecha y hora de la venta

    // Constructor
    public Sale(UUID id, UUID userId,PayMethod paymentMethod, List<SaleProduct> products, BigDecimal totalAmount, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.paymentMethod=paymentMethod;
        this.products = products;
        this.totalAmount = totalAmount;
        this.timestamp = timestamp;
    }

    public BigDecimal calculateTotal(){
        this.totalAmount = products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return this.totalAmount;
    }

    public UUID getId() {
        return id;
    }


    public PayMethod getPaymentMethod(){return paymentMethod;}

    public UUID getUserId() {
        return userId;
    }

    public List<SaleProduct> getProducts() {
        return products;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
