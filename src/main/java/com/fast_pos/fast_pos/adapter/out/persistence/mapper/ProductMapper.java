package com.fast_pos.fast_pos.adapter.out.persistence.mapper;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.ProductEntity;
import com.fast_pos.fast_pos.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
        entity.setCategory(product.getCategory());
        return entity;
    }

    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock(),
                entity.getCategory()
        );
    }
}
