package com.java.ravito_plan.food.infrastructure.presenter.food.showFoodsByIds;

import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import java.util.Map;

public record ShowFoodsByIdsDto(Map<Long, FoodDetail> foods) {

}
