package com.fast_pos.fast_pos.domain.port.in;
import com.fast_pos.fast_pos.domain.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductUseCase {
    Product createProduct (Product product);
    Product updateProduct(UUID prductId,Product product);
    List<Product> createProducts(MultipartFile productsFile);
    Optional<Product> getProductById (UUID productId);
    List<Product> getAllProducts();
}
