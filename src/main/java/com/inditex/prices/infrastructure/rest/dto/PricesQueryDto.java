package com.inditex.prices.infrastructure.rest.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Parámetros para consultar precios vigentes")
public class PricesQueryDto {
    @NotNull
    @Schema(description = "Fecha en formato yyyy-MM-dd'T'HH:mm:ss.SSS", example = "2020-06-14T18:30:00.000")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @NotNull
    @Schema(description = "ID del producto", example = "35455")
    private Integer productId;

    @Schema(description = "ID de la marca", example = "1")
    @NotNull
    private Integer brandId;
}
