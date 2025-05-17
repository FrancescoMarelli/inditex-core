package com.inditex.prices.infrastructure.persistence;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infrastructure.persistence.entity.PricesEntity;
import com.inditex.prices.infrastructure.persistence.mapper.PricesPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PricesAdapter implements PricesPort {
    private final PricesJpaRepository repository;
    private final PricesPersistenceMapper mapper;

    @Override
    public Prices createPrices(Prices prices) {
        PricesEntity entity = mapper.toEntity(prices);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public List<Prices> getValidPricesByProductIdAndBrandId(LocalDateTime date, Integer productId, Integer brandId) {
        return mapper.toDomainList(repository.getValidPricesByProductIdAndBrandId(date, productId, brandId));
    }
}
