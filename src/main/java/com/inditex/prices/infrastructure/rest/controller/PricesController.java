package com.inditex.prices.infrastructure.rest.controller;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.CreatePricesUseCase;
import com.inditex.prices.domain.ports.GetPricesUseCase;
import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import com.inditex.prices.infrastructure.rest.dto.PricesQueryDto;
import com.inditex.prices.infrastructure.rest.mapper.PricesRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PricesController implements PricesControllerV1 {
    private final CreatePricesUseCase createPromotionService;
    private final GetPricesUseCase getPricesUseCase;
    private final PricesRestMapper pricesRestMapper;

    public ResponseEntity<PricesDto> createPrices(@RequestBody PricesDto dto) {
        Prices prices = createPromotionService.createPrice(pricesRestMapper.toDomain(dto));
        return ResponseEntity.ok(pricesRestMapper.toDto(prices));
    }

    public ResponseEntity<PricesDto> pricesInformation(@Valid PricesQueryDto dto) {
        Prices prices = getPricesUseCase
            .getValidPricesByProductIdAndBrandId(dto.getDate(), dto.getProductId(), dto.getBrandId());
        return ResponseEntity.ok(pricesRestMapper.toDto(prices));
    }

}
