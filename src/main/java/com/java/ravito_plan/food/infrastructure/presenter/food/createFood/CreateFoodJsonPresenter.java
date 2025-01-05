package com.java.ravito_plan.food.infrastructure.presenter.food.createFood;

import com.java.ravito_plan.food.application.mapper.FoodViewMapper;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CreateFoodJsonPresenter implements CreateFoodPresenter {

    CreateFoodViewModel viewModel;

    @Override
    public void present(CreateFoodResponse response) {
        this.viewModel = new CreateFoodViewModel(FoodViewMapper.toFoodView(response.food()));
    }
}
