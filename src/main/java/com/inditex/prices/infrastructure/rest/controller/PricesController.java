package com.inditex.prices.infrastructure.rest.controller;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.CreatePricesUseCase;
import com.inditex.prices.domain.ports.GetPricesUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import com.inditex.prices.infrastructure.rest.mapper.PricesRestMapper;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PricesController {
    private final CreatePricesUseCase createPromotionService;
    private final GetPricesUseCase getPricesUseCase;
    private final PricesRestMapper pricesRestMapper;

    @PostMapping("/create-prices")
    public ResponseEntity<PricesDto> createPrices(@RequestBody PricesDto dto) {
        Prices prices = createPromotionService.createPrice(pricesRestMapper.toDomain(dto));
        return ResponseEntity.ok(pricesRestMapper.toDto(prices));
    }

    @GetMapping("/get-prices-info")
    public ResponseEntity<PricesDto> pricesInformation(
        @Parameter(description = "Fecha en formato yyyy-MM-ddTHH:mm:ss.SSS")
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  date,
        @RequestParam Integer productId,
        @RequestParam Integer brandId) {

        Prices prices = getPricesUseCase
            .getValidPricesByProductIdAndBrandId(date, productId, brandId);
        return ResponseEntity.ok(pricesRestMapper.toDto(prices));
    }

}
