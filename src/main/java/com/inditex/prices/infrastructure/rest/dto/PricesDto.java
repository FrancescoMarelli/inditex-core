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
    @Schema(example = "1")
    private Integer brandId;
    @Schema(example = "1")
    private String productId;
    @Schema(example = "2025-05-14T10:00:00")
    private LocalDateTime startDate;
    @Schema(example = "2025-05-18T10:00:00")
    private LocalDateTime endDate;
    @Schema(example = "1")
    private Integer priceList;
    @Schema(example = "1")
    private Integer priority;
    @Schema(example = "23.3")
    private Double price;
    @Schema(example = "EUR")
    private String currencyCode;
}
