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
public class PricesQueryDto {
    @Schema(example = "1")
    private Integer brandId;
    @Schema(example = "2025-05-14T10:00:00")
    private LocalDateTime date;
    @Schema(example = "35455")
    private Integer productId;
}
