package com.fast_pos.fast_pos.adapter.in.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class SaleItemResponse {
    private UUID productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

}
