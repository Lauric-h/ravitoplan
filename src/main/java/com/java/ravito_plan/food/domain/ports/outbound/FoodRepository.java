package com.java.ravito_plan.food.domain.ports.outbound;

import com.java.ravito_plan.food.domain.model.Food;
import java.util.List;

public interface FoodRepository {

    Food findById(Long id);

    Food findByIdAndBrandId(Long id, Long brandId);

    Food save(Food food);

    List<Food> findAllByBrandId(Long brandId);

    void deleteByIdAndBrandId(Long id, Long brandId);
}
