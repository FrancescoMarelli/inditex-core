package com.inditex.prices.infrastructure.rest.exceptions;

public record ErrorResponse(
    String message,
    int status,
    String timestamp
) {}
