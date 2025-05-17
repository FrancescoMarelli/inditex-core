package com.inditex.prices.application;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.CreatePricesUseCase;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infrastructure.rest.exceptions.InvalidCurrencyCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
@RequiredArgsConstructor
public class CreatePricesService implements CreatePricesUseCase {

    private final PricesPort port;

    @Override
    public Prices createPrice(Prices prices) {
        validateCurrencyCode(prices.getCurrencyCode());
        return port.createPrices(prices);
    }

    private void validateCurrencyCode(String code) {
        try {
            Currency.getInstance(code);
        } catch (IllegalArgumentException e) {
            throw new InvalidCurrencyCodeException("Código de moneda inválido: " + code);
        }
    }
}
