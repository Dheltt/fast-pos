package com.fast_pos.fast_pos.adapter.in.dto.response;

import com.fast_pos.fast_pos.domain.enums.PayMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SaleResponse {
    private UUID id;
    private UUID userId;
    private PayMethod payMethod;
    private List<SaleItemResponse> items;
    private BigDecimal totalAmount;
    private LocalDateTime timestamp;
}
