package com.inditex.prices.infrastructure.persistence.mapper;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.infrastructure.persistence.entity.PricesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricesPersistenceMapper {
    PricesEntity toEntity(Prices prices);
    Prices toDomain(PricesEntity entity);

    List<PricesEntity> toEntityList(List<Prices> pricesList);

    List<Prices> toDomainList(List<PricesEntity> promotionEntities);

}
