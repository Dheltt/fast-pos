package com.fast_pos.fast_pos.adapter.in.controllers;

import com.fast_pos.fast_pos.adapter.in.dto.request.ProductRequest;
import com.fast_pos.fast_pos.adapter.in.dto.response.ProductCsvDTO;
import com.fast_pos.fast_pos.adapter.in.dto.response.ProductResponse;
import com.fast_pos.fast_pos.adapter.in.mapper.ProductWebMapper;
import com.fast_pos.fast_pos.adapter.out.persistence.entity.ProductEntity;
import com.fast_pos.fast_pos.application.exceptions.ProductNotFoundException;
import com.fast_pos.fast_pos.application.service.ProductService;
import com.fast_pos.fast_pos.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
//@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "http://localhost:3000")
@RestController @RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    private final ProductWebMapper productWebMapper;

    public ProductController(ProductService productService,ProductWebMapper productWebMapper){
        this.productService=productService;
        this.productWebMapper=productWebMapper;
    }
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request){
        Product product = productWebMapper.toDomain(request);
        Product productSaved = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productWebMapper.toResponse(productSaved));
    }

    @Operation(summary = "Upload CSV file with products", description = "Bulk upload products via CSV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Products created"),
            @ApiResponse(responseCode = "400", description = "Invalid file")
    })
    @PostMapping(value = "/bulk", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ProductResponse>> createProducts(@Parameter(description = "CSV file containing products", required = true)
                                                                    @RequestPart("file") MultipartFile file){
        if(file.isEmpty()) throw new IllegalArgumentException("Uploaded file is empty");
        List<Product> products = productService.createProducts(file);
        List<ProductResponse> responses = products.stream().map(productWebMapper::toResponse)
               .toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@Parameter(description = "ID of the product to update")@PathVariable UUID productId,@RequestBody ProductRequest request){
        Product newData = productWebMapper.toDomain(request);

        Product productUpdated = productService.updateProduct(productId,newData);
        return ResponseEntity.ok(productWebMapper.toResponse(productUpdated));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductResponse> responses = products.stream().map(productWebMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID productId){
        return productService.getProductById(productId)
                .map(product -> ResponseEntity.ok(productWebMapper.toResponse(product)))
                .orElseThrow(()->new ProductNotFoundException(productId));
    }
}
