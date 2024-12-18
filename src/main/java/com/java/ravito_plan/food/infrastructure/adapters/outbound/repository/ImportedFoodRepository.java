package com.java.ravito_plan.food.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.food.domain.model.Food;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedFoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByIdAndBrandId(Long id, Long brandId);
    Void deleteByIdAndBrandId(Long id, Long brandId);
    List<Food> findAllByBrandId(Long brandId);
}
