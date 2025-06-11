package com.fast_pos.fast_pos.application.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message){
        super(message);
    }
}
