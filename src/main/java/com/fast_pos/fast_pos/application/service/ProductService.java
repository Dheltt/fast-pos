package com.fast_pos.fast_pos.application.service;

import com.fast_pos.fast_pos.adapter.in.dto.response.ProductCsvDTO;
import com.fast_pos.fast_pos.adapter.in.dto.response.ProductResponse;
import com.fast_pos.fast_pos.application.exceptions.ProductNotFoundException;
import com.fast_pos.fast_pos.application.util.ParseCsvFile;
import com.fast_pos.fast_pos.domain.model.Product;
import com.fast_pos.fast_pos.domain.port.in.ProductUseCase;
import com.fast_pos.fast_pos.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductService implements ProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;
    private final ParseCsvFile parseCsvFile;

    public ProductService(ProductRepositoryPort productRepositoryPort,ParseCsvFile parseCsvFile){
        this.productRepositoryPort=productRepositoryPort;
        this.parseCsvFile=parseCsvFile;
    }
    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }


    @Override
    public Product updateProduct(UUID productId,Product product) {

        Product existingProduct = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        existingProduct.updateName(product.getName());
        existingProduct.updatePrice(product.getPrice());
        existingProduct.updateStock(product.getStock());
        return productRepositoryPort.updateProduct(existingProduct);
    }

    @Override
    public List<Product> createProducts(MultipartFile productsFile) {
        List<Product> products;
        try{
            products = parseCsvFile.parseCsvFile(productsFile);
        }catch(IOException e){
            throw new RuntimeException("Failde to parse CSV file, "+e);
        }
        return productRepositoryPort.saveAll(products);
    }

    @Override
    public Optional<Product> getProductById(UUID productId) {
        return productRepositoryPort.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }
}
