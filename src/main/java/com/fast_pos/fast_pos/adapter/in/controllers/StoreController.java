package com.fast_pos.fast_pos.adapter.in.controllers;

import com.fast_pos.fast_pos.adapter.in.dto.request.RegisterStoreRequest;
import com.fast_pos.fast_pos.application.service.StoreRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin(origins = "http://localhost:3000")
@RestController @RequestMapping("api/stores")
public class StoreController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
    private final StoreRegistrationService storeRegistrationService;

    public StoreController(StoreRegistrationService storeRegistrationService){
        this.storeRegistrationService = storeRegistrationService;
    }
   @PostMapping(value="/register",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
   @Operation(
           summary = "Register a new store", description = "Creates schema and owner user for a new store",
           requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                   content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
           )
   )
   @ApiResponse(responseCode = "201", description = "Store registered successfully")
   public ResponseEntity<String> registerStore(
           @ModelAttribute
           RegisterStoreRequest registerStoreRequest,
           @RequestPart("file") MultipartFile file) {

       logger.info("Received register store request: {}", registerStoreRequest);
       logger.info("Received file: name = {}, size = {} bytes, contentType = {}",
               file.getOriginalFilename(), file.getSize(), file.getContentType());
       try {
           storeRegistrationService.registerNewStore(registerStoreRequest, file);
           logger.info("Store registration successful");
           return ResponseEntity.status(HttpStatus.CREATED).build();
       } catch (Exception e) {
           logger.error("Error during store registration", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Error during store registration: " + e.getMessage());
       }
   }
}
