package com.inditex.prices.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<String> handlePriceNotFound(PriceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno de Server");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == LocalDateTime.class) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Formato de fecha inválido. Usa el formato yyyy-MM-dd HH:mm:ss");
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Parámetro inválido: " + ex.getName());
    }
}
