package com.fast_pos.fast_pos.domain.port.out;

import com.fast_pos.fast_pos.domain.model.Sale;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface SaleRepositoryPort {
    Sale save(Sale sale);
    Optional<Sale> findById(UUID saleId);
    List<Sale> findAll();
}
