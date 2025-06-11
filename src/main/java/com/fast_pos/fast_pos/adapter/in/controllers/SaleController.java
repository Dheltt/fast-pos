package com.fast_pos.fast_pos.adapter.in.controllers;

import com.fast_pos.fast_pos.adapter.in.dto.request.SaleRequest;
import com.fast_pos.fast_pos.adapter.in.dto.response.SaleResponse;
import com.fast_pos.fast_pos.adapter.in.mapper.SaleWebMapper;
import com.fast_pos.fast_pos.application.service.SaleService;
import com.fast_pos.fast_pos.domain.model.Sale;
import com.fast_pos.fast_pos.domain.model.SaleProduct;
import com.fast_pos.fast_pos.domain.port.in.SaleUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://localhost:3000")
public class SaleController {
    private final SaleService saleService;
    private final SaleWebMapper saleWebMapper;

    public SaleController(SaleService saleService,SaleWebMapper saleWebMapper) {
        this.saleService = saleService;
        this.saleWebMapper=saleWebMapper;
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@RequestBody SaleRequest saleRequest) {
        List<SaleProduct> productsConInfo = saleService.mapToSaleProducts(saleRequest.getProducts());

        Sale sale = saleWebMapper.toDomain(saleRequest,productsConInfo);
        sale.calculateTotal();
        Sale created = saleService.createSale(sale);
        SaleResponse response = saleWebMapper.toResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable UUID id) {
        Sale sale = saleService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }


    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        return ResponseEntity.ok(saleService.getAllSales());
    }
}
