package com.java.ravito_plan.food.infrastructure.presenter.food.showFood;

import com.java.ravito_plan.food.application.mapper.FoodViewMapper;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShowFoodJsonPresenter implements ShowFoodPresenter {

    private ShowFoodViewModel viewModel;

    @Override
    public void present(ShowFoodResponse response) {
        this.viewModel = new ShowFoodViewModel(FoodViewMapper.toFoodView(response.food()));
    }
}
