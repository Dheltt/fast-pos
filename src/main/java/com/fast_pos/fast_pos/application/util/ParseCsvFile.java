package com.fast_pos.fast_pos.application.util;

import com.fast_pos.fast_pos.adapter.in.dto.response.ProductCsvDTO;
import com.fast_pos.fast_pos.domain.model.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Component
public class ParseCsvFile {
    public List<Product> parseCsvFile(MultipartFile file) throws IOException {
        List<Product> products = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            for (CSVRecord record : csvParser) {
                String name = record.get("name");
                BigDecimal price = new BigDecimal(record.get("price"));
                int stock = Integer.parseInt(record.get("stock"));
                String category = record.get("category");
                products.add(new Product(null,name, price, stock,category));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV", e);
        }        return products;
    }
}
