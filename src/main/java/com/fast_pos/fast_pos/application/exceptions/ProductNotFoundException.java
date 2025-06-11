package com.fast_pos.fast_pos.application.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends EntityNotFoundException{
    public ProductNotFoundException(UUID productId){
        super("Product not foudn with id: "+productId);
    }
}
