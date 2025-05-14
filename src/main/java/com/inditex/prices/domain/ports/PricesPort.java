package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesPort {
    Prices getPromotionById(Integer id);
    List<Prices> getAllPrices();

    Prices createPrices(Prices prices);

    Prices updatePromotion(Integer id, Prices prices);


    List<Prices> getValidPricesByProductIdAndBrandId(LocalDateTime date, Integer productId, Integer brandId);

    void deletePricesById(Integer id);
}
