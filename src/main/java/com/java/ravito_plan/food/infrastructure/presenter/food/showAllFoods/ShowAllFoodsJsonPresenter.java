package com.java.ravito_plan.food.infrastructure.presenter.food.showAllFoods;

import com.java.ravito_plan.food.application.mapper.FoodViewMapper;
import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoodsPresenter;
import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoodsResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShowAllFoodsJsonPresenter implements ShowAllFoodsPresenter {

    ShowAllFoodsViewModel viewModel;

    @Override
    public void present(ShowAllFoodsResponse response) {
        this.viewModel = new ShowAllFoodsViewModel(response.foods().stream().map(FoodViewMapper::toFoodSummaryView).toList());
    }
}
