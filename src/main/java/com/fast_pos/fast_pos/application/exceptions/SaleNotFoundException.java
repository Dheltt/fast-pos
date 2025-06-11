package com.fast_pos.fast_pos.application.exceptions;

import java.util.UUID;

public class SaleNotFoundException extends RuntimeException{
    public SaleNotFoundException(UUID saleId){
        super("Sale not found with id: "+saleId);
    }
}
