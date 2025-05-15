package com.inditex.prices.infrastructure.rest;

public record ErrorResponse(
    String message,
    int status,
    String timestamp
) {}
