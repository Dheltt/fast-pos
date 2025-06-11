package com.fast_pos.fast_pos.adapter.in.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class SaleItemRequest {
    @NotNull
    private UUID productId;
    @Min(1)
    private int quantity;
}
