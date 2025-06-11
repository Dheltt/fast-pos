package com.fast_pos.fast_pos.application.exceptions;

import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(UUID userId){
        super("User not found with id: "+userId);
    }
}
