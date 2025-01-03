package com.java.ravito_plan.food.domain.usecase.food.showFood;

import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowFood implements ShowFoodInterface {

    private final FoodRepository foodRepository;

    public ShowFood(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowFoodRequest request, ShowFoodPresenter presenter) {
        Food food = this.foodRepository.findById(request.foodId());
        presenter.present(new ShowFoodResponse(food));
    }
}
