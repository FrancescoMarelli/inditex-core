package com.inditex.prices.infrastructure.persistence;


import com.inditex.prices.infrastructure.persistence.entity.PricesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesJpaRepository extends CrudRepository<PricesEntity, Integer> {
    @Query("SELECT p FROM PricesEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :date >= p.startDate AND :date <= p.endDate")
    List<PricesEntity> getValidPricesByProductIdAndBrandId(@Param("date") LocalDateTime date,
                                                           @Param("productId") Integer productId,
                                                           @Param("brandId") Integer brandId);



}
