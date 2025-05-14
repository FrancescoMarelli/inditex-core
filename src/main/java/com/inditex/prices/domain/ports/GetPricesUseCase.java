package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.model.Prices;

import java.time.LocalDateTime;

public interface GetPricesUseCase {
    Prices getValidPricesByProductIdAndBrandId(LocalDateTime date, Integer productId, Integer brandId);
}
