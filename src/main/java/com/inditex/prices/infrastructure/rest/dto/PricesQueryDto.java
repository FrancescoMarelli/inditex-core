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
    private Integer brandId;
    private LocalDateTime date;
    private Integer productId;
}
