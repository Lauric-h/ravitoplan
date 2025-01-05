package com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds;

import com.java.ravito_plan.food.domain.model.Food;
import java.util.List;

public record ShowFoodsByIdsResponse(List<Food> foods) {

}
