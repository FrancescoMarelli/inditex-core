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
    public Prices getPromotionById(Integer id) {
        Optional<PricesEntity> promotionEntity = repository.findById(id);
        return mapper.toDomain(promotionEntity.orElseThrow());
    }

    @Override
    public List<Prices> getAllPrices() {
        List<PricesEntity> pricesEntityList = (List<PricesEntity>) repository.findAll();
        return mapper.toDomainList(pricesEntityList);
    }

    @Override
    public Prices createPrices(Prices prices) {
        PricesEntity pricesEntity = repository.save(mapper.toEntity(prices));
        return mapper.toDomain(pricesEntity);
    }

    @Override
    public Prices updatePromotion(Integer id, Prices prices) {
         return mapper.toDomain(repository.save(mapper.toEntity(prices)));
    }

    @Override
    public List<Prices> getValidPricesByProductIdAndBrandId(LocalDateTime date, Integer productId, Integer brandId) {
        return mapper.toDomainList(repository.getValidPricesByProductIdAndBrandId(date, productId, brandId));
    }

    @Override
    public void deletePricesById(Integer id) {
        repository.deleteById(id);
    }
}
