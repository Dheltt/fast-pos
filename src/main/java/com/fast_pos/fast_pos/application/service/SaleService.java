package com.fast_pos.fast_pos.application.service;

import com.fast_pos.fast_pos.adapter.in.dto.request.SaleItemRequest;
import com.fast_pos.fast_pos.application.exceptions.SaleNotFoundException;
import com.fast_pos.fast_pos.domain.model.Product;
import com.fast_pos.fast_pos.domain.model.Sale;
import com.fast_pos.fast_pos.domain.model.SaleProduct;
import com.fast_pos.fast_pos.domain.port.in.SaleUseCase;
import com.fast_pos.fast_pos.domain.port.out.SaleRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@Service
public class SaleService implements SaleUseCase {
    private final SaleRepositoryPort saleRepository;
    private final ProductService productService;

    public SaleService(SaleRepositoryPort saleRepository, ProductService productService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale getSaleById(UUID saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException(saleId));
    }
    public List<SaleProduct> mapToSaleProducts(List<SaleItemRequest> itemRequests) {
        return itemRequests.stream().map(item -> {
            Product product = productService.getProductById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductId()));

            return new SaleProduct(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    item.getQuantity()
            );
        }).toList();
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
