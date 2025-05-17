package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.model.Prices;

public interface CreatePricesUseCase {
    Prices createPrice(Prices prices);
}
