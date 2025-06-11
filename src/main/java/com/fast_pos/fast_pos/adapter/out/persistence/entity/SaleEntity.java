package com.fast_pos.fast_pos.adapter.out.persistence.entity;

import com.fast_pos.fast_pos.domain.enums.PayMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity @Table(name="sales")
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class SaleEntity {
    @Id  @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PayMethod paymentMethod;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<SaleProductEntity> products = new ArrayList<>();

    @Column(name = "total_amount",nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
