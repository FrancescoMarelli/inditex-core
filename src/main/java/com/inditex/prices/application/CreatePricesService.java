package com.inditex.prices.application;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.CreatePricesUseCase;
import com.inditex.prices.domain.ports.PricesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePricesService implements CreatePricesUseCase {

    private final PricesPort port;

    @Override
    public Prices createPrice(Prices prices) {
        return port.createPrices(prices);
    }
}
