package com.java.ravito_plan.food.domain.ports.outbound;

import com.java.ravito_plan.food.domain.model.Food;
import java.util.List;

public interface FoodRepository {

    Food findById(Long id);

    Food save(Food food);

    List<Food> findAll();

    void deleteById(Long id);
}
