package com.java.ravito_plan.race.infrastructure.adapters;

import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.application.service.FoodApplicationService;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodInterface;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodRequest;
import com.java.ravito_plan.food.infrastructure.presenter.food.showFood.ShowFoodRacePresenter;
import com.java.ravito_plan.food.infrastructure.presenter.food.showFood.ShowFoodRaceDto;
import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FoodPortAdapter implements FoodPort {

    private final FoodApplicationService foodApplicationService;
    private final ShowFoodInterface showFoodUsecase;
    private final ShowFoodRacePresenter showFoodPresenter;

    public FoodPortAdapter(FoodApplicationService foodApplicationService,
            ShowFoodInterface showFoodUsecase, ShowFoodRacePresenter showFoodPresenter) {
        this.foodApplicationService = foodApplicationService;
        this.showFoodUsecase = showFoodUsecase;
        this.showFoodPresenter = showFoodPresenter;
    }

    @Override
    public RaceFoodDto getFoodById(Long id) {
        this.showFoodUsecase.execute(new ShowFoodRequest(id), this.showFoodPresenter);

        return this.mapToRaceFoodDto(this.showFoodPresenter.getViewModel());
    }

    private RaceFoodDto mapToRaceFoodDto(ShowFoodRaceDto viewModel) {
        return new RaceFoodDto(viewModel.food().id, viewModel.food().brandName,
                viewModel.food().name, viewModel.food().carbohydrates, viewModel.food().calories,
                viewModel.food().proteins, viewModel.food().electrolytes, viewModel.food().link,
                viewModel.food().comment, viewModel.food().ingestionType);
    }

    @Override
    public Map<Long, RaceFoodDto> getFoodsByIds(Collection<Long> ids) {
        Map<Long, FoodDetail> foods = this.foodApplicationService.getFoodsByIds(ids);
        return foods.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
            FoodDetail foodDetail = entry.getValue();
            return new RaceFoodDto(foodDetail.id, foodDetail.brandName, foodDetail.name,
                    foodDetail.carbohydrates, foodDetail.calories, foodDetail.proteins,
                    foodDetail.electrolytes, foodDetail.link, foodDetail.comment,
                    foodDetail.ingestionType);
        }));
    }
}
