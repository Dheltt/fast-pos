package com.fast_pos.fast_pos.domain.port.in;

import com.fast_pos.fast_pos.domain.model.Sale;

import java.util.List;
import java.util.UUID;

public interface SaleUseCase {
    Sale createSale(Sale sale);
    Sale getSaleById(UUID saleId);
    List<Sale> getAllSales();
}
