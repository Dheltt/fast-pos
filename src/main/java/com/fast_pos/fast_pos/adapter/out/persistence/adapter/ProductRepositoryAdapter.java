package com.fast_pos.fast_pos.adapter.out.persistence.adapter;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.ProductEntity;
import com.fast_pos.fast_pos.adapter.out.persistence.jpa.JpaProductRepository;
import com.fast_pos.fast_pos.adapter.out.persistence.mapper.ProductMapper;
import com.fast_pos.fast_pos.application.exceptions.ProductNotFoundException;
import com.fast_pos.fast_pos.domain.model.Product;
import com.fast_pos.fast_pos.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository,

                                    ProductMapper productMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity saved = jpaProductRepository.save(entity);
        return productMapper.toDomain(saved);
    }

    @Override
    public Product updateProduct(Product product) {
        UUID productId = product.getId();
        ProductEntity existingEntity = jpaProductRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(product.getId()));

        ProductEntity  updatedEntity = productMapper.toEntity(product);

        updatedEntity.setId(existingEntity.getId());
        ProductEntity saved = jpaProductRepository.save(updatedEntity);
        return productMapper.toDomain(saved);
    }



    @Override
    public List<Product> saveAll(List<Product> products) {
        List<ProductEntity> entities = products.stream()
                .map(productMapper::toEntity)
                .toList();
        List<ProductEntity> savedEntities = jpaProductRepository.saveAll(entities);
        return savedEntities.stream()
                .map(productMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaProductRepository.findById(id)
                .map(productMapper::toDomain);
    }
    @Override
    public List<Product> findAll(){
        return jpaProductRepository.findAll()
                .stream()
                .map(productMapper::toDomain)
                .toList();
    }

}
