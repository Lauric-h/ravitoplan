package com.java.ravito_plan.food.domain.service;

import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.application.dto.FoodDto;
import java.util.List;

public interface FoodService {

    List<FoodDto> getAllFoodsByBrand(Long brandId);

    FoodDto getFood(Long id, Long brandId);

    BrandFullDto createFood(String name, int carbohydrates, int calories, int proteins,
            boolean electrolytes, String link, String comment, String type, Long brandId);

    void deleteFood(Long id, Long brandId);

    void updateFood(FoodDto foodDto, Long foodId, Long brandId);
}
