package com.java.ravito_plan.food.infrastructure.presenter.food.updateFood;

import com.java.ravito_plan.food.application.mapper.FoodViewMapper;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFoodResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UpdateFoodJsonPresenter implements UpdateFoodPresenter {

    private UpdateFoodViewModel viewModel;

    @Override
    public void present(UpdateFoodResponse response) {
       this.viewModel = new UpdateFoodViewModel(FoodViewMapper.toFoodView(response.food()));
    }
}
