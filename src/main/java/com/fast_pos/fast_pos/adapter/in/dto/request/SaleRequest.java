package com.fast_pos.fast_pos.adapter.in.dto.request;

import com.fast_pos.fast_pos.domain.enums.PayMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class SaleRequest {
    private UUID userId;
    private PayMethod paymentMethod;
    private List<SaleItemRequest> products;
}
