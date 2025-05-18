package com.inditex.prices.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricesDto {
    @Schema(description = "Identificador único para la marca a la que pertenece el producto", example = "1")
    private Integer brandId;

    @Schema(description = "Identificador único para cada producto", example = "1")
    private String productId;

    @Schema(description = "Indica la fecha de inicio del precio y tarifa", example = "2025-05-14T10:00:00")
    private LocalDateTime startDate;

    @Schema(description = "Indica la fecha de fin del precio y tarifa", example = "2025-05-18T10:00:00")
    private LocalDateTime endDate;

    @Schema(description = "Identificador de una tarifa aplicada a un producto", example = "1")
    private Integer priceList;

    @Schema(description = "Valor que indica que la tarifa con la prioridad más alta es la que aplica", example = "1")
    private Integer priority;

    @Schema(description = " Indica el precio final de venta del producto", example = "23.3")
    private Double price;

    @Schema(description = "Codigo ISO de la moneda en la que se establece el precio", example = "EUR")
    private String currencyCode;
}
