package com.java.ravito_plan.food.application.usecase.food.showFood;

import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFood;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodRequest;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowFoodImpl implements ShowFood {

    private final FoodRepository foodRepository;

    public ShowFoodImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowFoodRequest request, ShowFoodPresenter presenter) {
        Food food = this.foodRepository.findById(request.foodId());
        presenter.present(new ShowFoodResponse(food));
    }
}
