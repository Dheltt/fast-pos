package com.fast_pos.fast_pos.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PayMethod {
    CASH,CREDIT_CARD,DEBIT_CARD,TRANSFER;

    @JsonCreator
    public static PayMethod fromString(String value) {
        return switch (value.toUpperCase()) {
            case "CASH" -> CASH;
            case "CARD", "CREDIT_CARD" -> CREDIT_CARD;
            case "DEBIT_CARD" -> DEBIT_CARD;
            case "TRANSFER" -> TRANSFER;
            default -> throw new IllegalArgumentException("Invalid payment method: " + value);
        };
    }
}
