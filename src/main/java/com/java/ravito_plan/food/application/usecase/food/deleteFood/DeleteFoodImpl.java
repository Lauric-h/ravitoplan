package com.java.ravito_plan.food.application.usecase.food.deleteFood;

import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFood;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodRequest;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteFoodImpl implements DeleteFood {

    private final FoodRepository foodRepository;

    public DeleteFoodImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional
    public void execute(DeleteFoodRequest request, DeleteFoodPresenter presenter) {
        this.foodRepository.deleteByIdAndBrandId(request.foodId(), request.brandId());
        presenter.present(new DeleteFoodResponse());
    }
}
