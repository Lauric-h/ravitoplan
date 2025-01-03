package com.java.ravito_plan.food.domain.usecase.food.deleteFood;

import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteFood implements DeleteFoodInterface {

    private final FoodRepository foodRepository;

    public DeleteFood(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional
    public void execute(DeleteFoodRequest request, DeleteFoodPresenter presenter) {
        this.foodRepository.deleteByIdAndBrandId(request.foodId(), request.brandId());
    }
}
