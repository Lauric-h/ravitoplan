package com.java.ravito_plan.food.infrastructure.presenter.food.showFoodsByIds;

import com.java.ravito_plan.food.application.mapper.FoodMapper;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds.ShowFoodsByIdsPresenter;
import com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds.ShowFoodsByIdsResponse;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShowFoodsByIdsRacePresenter implements ShowFoodsByIdsPresenter {

    private ShowFoodsByIdsViewModel viewModel;

    @Override
    public void present(ShowFoodsByIdsResponse response) {
        this.viewModel = new ShowFoodsByIdsViewModel(response.foods().stream()
                .collect(Collectors.toMap(Food::getId, FoodMapper::toFoodDetail)));

    }
}
