package com.fast_pos.fast_pos.adapter.out.persistence.jpa;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {

}
