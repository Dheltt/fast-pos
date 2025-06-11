package com.fast_pos.fast_pos.adapter.in.mapper;

import com.fast_pos.fast_pos.adapter.in.dto.request.SaleRequest;
import com.fast_pos.fast_pos.adapter.in.dto.response.SaleItemResponse;
import com.fast_pos.fast_pos.adapter.in.dto.response.SaleResponse;
import com.fast_pos.fast_pos.domain.model.Sale;
import com.fast_pos.fast_pos.domain.model.SaleProduct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Component
public class SaleWebMapper {
    public Sale toDomain(SaleRequest request, List<SaleProduct> productosConInfo) {
        return new Sale(
                UUID.randomUUID(),
                request.getUserId(),
                request.getPaymentMethod(),
                productosConInfo,
                BigDecimal.ZERO, // El total se calcula en el caso de uso
                LocalDateTime.now()
        );
    }

    public SaleResponse toResponse(Sale sale) {
        List<SaleItemResponse> items = sale.getProducts().stream().map(product ->
                new SaleItemResponse(
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getQuantity()
                )
        ).toList();

        return new SaleResponse(
                sale.getId(),
                sale.getUserId(),
                sale.getPaymentMethod(),
                items,
                sale.getTotalAmount(),
                sale.getTimestamp()
        );
    }
}
