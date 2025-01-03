package com.java.ravito_plan.food.infrastructure.presenter.food.deleteFood;

import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DeleteFoodJsonPresenter implements DeleteFoodPresenter {

    DeleteFoodViewModel viewModel;

    @Override
    public void present(DeleteFoodResponse response) {
        this.viewModel = new DeleteFoodViewModel();
    }
}
