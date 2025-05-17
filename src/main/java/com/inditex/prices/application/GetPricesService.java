package com.inditex.prices.application;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.GetPricesUseCase;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infrastructure.rest.exceptions.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPricesService implements GetPricesUseCase {
    private final PricesPort port;

    @Override
    public Prices getValidPricesByProductIdAndBrandId(LocalDateTime date, Integer productId, Integer brandId) {
        List<Prices> pricesList = port.getValidPricesByProductIdAndBrandId(date, productId, brandId);

        return pricesList.stream()
            .max(Comparator.comparingInt(Prices::getPriority))
            .orElseThrow(() -> new PriceNotFoundException("No se encontró una tarifa válida para esa fecha."));
    }

}
