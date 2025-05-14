package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.model.Prices;

public interface UpdatePricesUseCase {
    Prices updatePrices(Integer id, Prices prices);

}
