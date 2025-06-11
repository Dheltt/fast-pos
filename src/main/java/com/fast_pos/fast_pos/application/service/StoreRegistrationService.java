package com.fast_pos.fast_pos.application.service;

import com.fast_pos.fast_pos.adapter.in.dto.request.RegisterStoreRequest;
import com.fast_pos.fast_pos.adapter.in.dto.response.ProductCsvDTO;
import com.fast_pos.fast_pos.application.util.ParseCsvFile;
import com.fast_pos.fast_pos.domain.model.Product;
import com.fast_pos.fast_pos.domain.port.in.RegisterStoreUseCase;
import com.fast_pos.fast_pos.domain.port.out.TenantManagementRepositoryPort;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreRegistrationService implements RegisterStoreUseCase {
    private final TenantManagementRepositoryPort tenantManagementRepositoryPort;
    private final ParseCsvFile parseCsvFile;
    private final PasswordEncoder passwordEncoder;

    public  StoreRegistrationService(TenantManagementRepositoryPort tenantManagementRepositoryPort,
                                     ParseCsvFile parseCsvFile,
                                     PasswordEncoder passwordEncoder){
        this.tenantManagementRepositoryPort=tenantManagementRepositoryPort;
        this.parseCsvFile=parseCsvFile;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void registerNewStore(RegisterStoreRequest request,MultipartFile productsFile) {
        String schemaName = request.getStoreName().toLowerCase().replaceAll("\\s+","_");
        tenantManagementRepositoryPort.createSchemaForTenant(schemaName);
        tenantManagementRepositoryPort.createTablesForTenant(schemaName);
        tenantManagementRepositoryPort.saveTenantInfo(schemaName, request.getStoreName(),request.getEmail());
        //encrypt the password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        tenantManagementRepositoryPort.createInitialOwnerUser(schemaName, request.getOwnerName(), request.getEmail(),encodedPassword);

        List<Product> products;
        try {
            products =parseCsvFile.parseCsvFile(productsFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file", e);
        }
        tenantManagementRepositoryPort.saveProducts(schemaName,products);
    }
}
