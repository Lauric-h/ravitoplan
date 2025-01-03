package com.java.ravito_plan.food.infrastructure.presenter.food.showAllFoods;

import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import java.util.List;

public record ShowAllFoodsViewModel(List<FoodSummaryView> foods) {

}
