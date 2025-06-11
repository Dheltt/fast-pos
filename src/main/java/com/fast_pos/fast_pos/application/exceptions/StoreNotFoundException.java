package com.fast_pos.fast_pos.application.exceptions;

import java.util.UUID;

public class StoreNotFoundException extends EntityNotFoundException{
    public StoreNotFoundException(UUID storeId){
        super("Store not found with id: "+storeId);
    }
}
