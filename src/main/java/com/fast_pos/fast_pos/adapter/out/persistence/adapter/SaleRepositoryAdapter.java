package com.fast_pos.fast_pos.adapter.out.persistence.adapter;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.SaleEntity;
import com.fast_pos.fast_pos.adapter.out.persistence.jpa.JpaSaleRepository;
import com.fast_pos.fast_pos.adapter.out.persistence.mapper.SaleMapper;
import com.fast_pos.fast_pos.domain.model.Sale;
import com.fast_pos.fast_pos.domain.port.out.SaleRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public class SaleRepositoryAdapter implements SaleRepositoryPort {
    private final JpaSaleRepository jpaSaleRepository;
    private final SaleMapper saleMapper;

    public SaleRepositoryAdapter(JpaSaleRepository jpaSaleRepository, SaleMapper saleMapper) {
        this.jpaSaleRepository = jpaSaleRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = saleMapper.toEntity(sale);
        SaleEntity savedEntity = jpaSaleRepository.save(entity);
        return saleMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Sale> findById(UUID saleId) {
        return jpaSaleRepository.findById(saleId).map(saleMapper::toDomain);
    }

    @Override
    public List<Sale> findAll() {
        return jpaSaleRepository.findAll().stream()
                .map(saleMapper::toDomain)
                .toList();
    }

}
