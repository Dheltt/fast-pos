package com.fast_pos.fast_pos.application.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordCheck {
    public static void main(String[] args) {
        String passwordIngresada = "1234";
        String hashGuardado = "$2a$10$ezJ0jNqkS6yJy88B3EcmzupJDPmieERmyD.eA6q6.cuMbC6HAWlHO";

        boolean passwordMatch = BCrypt.checkpw(passwordIngresada, hashGuardado);

        if (passwordMatch) {
            System.out.println("Contraseña correcta");
        } else {
            System.out.println("Contraseña incorrecta");
        }
        System.out.println("papuuu");
        String password = "1234";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        System.out.println("Hash para 1234: " + hashed);
    }

}
