package com.java.ravito_plan.food.application.mapper;

import com.java.ravito_plan.food.application.dto.command.CreateFoodCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateFoodCommand;
import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.domain.dto.FoodCreationParams;
import com.java.ravito_plan.food.domain.dto.FoodUpdateParams;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodRequest;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFoodRequest;

public class FoodMapper {

    public static FoodDetail toFoodDetail(Food food) {
        return new FoodDetail(food.getId(), food.getBrand().getName(), food.getName(),
                food.getCarbohydrates(), food.getCalories(), food.getProteins(),
                food.isElectrolytes(), food.getLink(), food.getComment(),
                food.getIngestionType().name());
    }

    public static CreateFoodRequest toCreateFoodRequest(Long brandId,
            CreateFoodCommand createFoodCommand) {
        return new CreateFoodRequest(brandId, new FoodCreationParams(createFoodCommand.getName(),
                createFoodCommand.getCarbohydrates(), createFoodCommand.getCalories(),
                createFoodCommand.getProteins(), createFoodCommand.isElectrolytes(),
                createFoodCommand.getLink(), createFoodCommand.getComment(),
                createFoodCommand.getIngestionType()));
    }

    public static UpdateFoodRequest toUpdateFoodRequest(Long foodId, Long brandId,
            UpdateFoodCommand command) {
        return new UpdateFoodRequest(foodId, brandId,
                new FoodUpdateParams(command.getName(), command.getCarbohydrates(),
                        command.getCalories(), command.getProteins(), command.isElectrolytes(),
                        command.getLink(), command.getComment(), command.getIngestionType()));
    }
}
