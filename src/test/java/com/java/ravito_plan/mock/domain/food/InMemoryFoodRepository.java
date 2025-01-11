package com.java.ravito_plan.mock.domain.food;

import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryFoodRepository implements FoodRepository {

  private final HashMap<Long, Food> foods = new HashMap<>();

  @Override
  public Food findById(Long id) {
    return this.foods.get(id);
  }

  @Override
  public List<Food> findAllByIdIn(List<Long> ids) {
    return this.foods.entrySet().stream()
        .filter(entry -> ids.contains(entry.getKey()))
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
  }

  @Override
  public Food findByIdAndBrandId(Long id, Long brandId) {
    return this.foods.entrySet().stream()
        .filter(map -> map.getKey().equals(id) && map.getValue().getBrand().getId().equals(brandId))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }

  @Override
  public Food save(Food food) {
    Food existingFood = this.foods.putIfAbsent(food.getId(), food);
    if (existingFood != null) {
      this.foods.replace(food.getId(), food);
    }
    return food;
  }

  @Override
  public List<Food> findAllByBrandId(Long brandId) {
    return this.foods.values().stream()
        .filter(food -> food.getBrand().getId().equals(brandId))
        .collect(Collectors.toList());
  }

  @Override
  public void deleteByIdAndBrandId(Long id, Long brandId) {
    this.foods.remove(id);
  }
}
