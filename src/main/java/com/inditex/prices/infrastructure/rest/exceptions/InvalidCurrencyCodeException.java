package com.inditex.prices.infrastructure.rest.exceptions;

public class InvalidCurrencyCodeException extends RuntimeException {
    public InvalidCurrencyCodeException(String message) {
        super(message);
    }
}

