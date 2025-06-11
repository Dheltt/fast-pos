package com.fast_pos.fast_pos.adapter.out.persistence.mapper;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.SaleEntity;
import com.fast_pos.fast_pos.adapter.out.persistence.entity.SaleProductEntity;
import com.fast_pos.fast_pos.domain.model.Sale;
import com.fast_pos.fast_pos.domain.model.SaleProduct;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SaleMapper {
    public SaleEntity toEntity(Sale sale) {
        SaleEntity entity = new SaleEntity();
        entity.setId(sale.getId());
        entity.setUserId(sale.getUserId());
        entity.setPaymentMethod(sale.getPaymentMethod());
        entity.setTotalAmount(sale.getTotalAmount());
        entity.setTimestamp(sale.getTimestamp());

        List<SaleProductEntity> productEntities = sale.getProducts().stream()
                .map(this::toEntity)
                .toList();

        productEntities.forEach(p -> p.setSale(entity)); // relación bidireccional
        entity.setProducts(productEntities);

        return entity;
    }

    public Sale toDomain(SaleEntity entity) {
        List<SaleProduct> products = entity.getProducts().stream()
                .map(this::toDomain)
                .toList();

        return new Sale(
                entity.getId(),
                entity.getUserId(),
                entity.getPaymentMethod(),
                products,
                entity.getTotalAmount(),
                entity.getTimestamp()
        );
    }

    public SaleProductEntity toEntity(SaleProduct product) {
        SaleProductEntity entity = new SaleProductEntity();
        entity.setProductId(product.getProductId());
        entity.setProductName(product.getProductName());
        entity.setPrice(product.getPrice());
        entity.setQuantity(product.getQuantity());
        return entity;
    }

    public SaleProduct toDomain(SaleProductEntity entity) {
        return new SaleProduct(
                entity.getProductId(),
                entity.getProductName(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }
}
