package com.fast_pos.fast_pos.domain.port.out;
import com.fast_pos.fast_pos.domain.model.Product;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
public interface ProductRepositoryPort {
    Product save(Product product);
    Product updateProduct(Product product);
    List<Product> saveAll(List<Product> products);
    Optional<Product> findById(UUID id);
    List<Product> findAll();
}
