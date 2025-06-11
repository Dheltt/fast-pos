package com.fast_pos.fast_pos.adapter.in.mapper;

import com.fast_pos.fast_pos.adapter.in.dto.request.ProductRequest;
import com.fast_pos.fast_pos.adapter.in.dto.response.ProductResponse;
import com.fast_pos.fast_pos.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper {

    public Product toDomain(ProductRequest request){
        return new Product(
                null,
                request.getName(),
                request.getPrice(),
                request.getStock(),
                request.getCategory()
        );
    }
    public ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
        );
    }

}
