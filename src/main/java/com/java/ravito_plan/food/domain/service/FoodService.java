package com.java.ravito_plan.food.domain.service;

import com.java.ravito_plan.food.application.dto.command.CreateFoodCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateFoodCommand;
import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodView;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FoodService {

    FoodDetail getFoodById(Long id);
    Map<Long, FoodDetail> getFoodsByIds(Collection<Long> ids);

    List<FoodSummaryView> getAllFoodsByBrand(Long brandId);
    FoodView getFood(Long id, Long brandId);

    BrandDetailView createFood(CreateFoodCommand createFoodCommand);
    void updateFood(UpdateFoodCommand updateFoodCommand);
    void deleteFood(Long id, Long brandId);
}
