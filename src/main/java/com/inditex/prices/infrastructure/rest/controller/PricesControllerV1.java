package com.inditex.prices.infrastructure.rest.controller;

import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import com.inditex.prices.infrastructure.rest.dto.PricesQueryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PricesControllerV1 {
    @Operation(
        summary = "Crear una promoción de precios",
        description = "Registra una nueva promoción para un producto en un rango de fechas determinado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Promoción creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/create-prices")
     ResponseEntity<PricesDto> createPrices(@RequestBody PricesDto dto);

    @Operation(
        summary = "Obtener precio aplicable",
        description = "Devuelve la información de precio vigente para un producto, marca y fecha proporcionados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Precio encontrado"),
        @ApiResponse(responseCode = "404", description = "No se encontró un precio válido para los parámetros proporcionados"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping("/get-prices-info")
     ResponseEntity<PricesDto> pricesInformation(@Valid PricesQueryDto dto);

}
