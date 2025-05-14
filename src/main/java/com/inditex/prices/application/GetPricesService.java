package com.inditex.prices.application;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.GetPricesUseCase;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infrastructure.rest.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPricesService implements GetPricesUseCase {
    private final PricesPort port;
    @Override
    public Prices getPriceById(UUID id) {
        return null;
    }

    @Override
    public List<Prices> getAllPrices() {
        return null;
    }

    @Override
    public Prices getValidPricesByProductIdAndBrandId(LocalDateTime date, Integer productId, Integer brandId) {
        List<Prices> pricesList = port.getValidPricesByProductIdAndBrandId(date, productId, brandId);

        return pricesList.stream()
            .min(Comparator.comparingInt(Prices::getPriority))
            .orElseThrow(() -> new PriceNotFoundException("No se encontró una tarifa válida para esa fecha."));
    }

}
