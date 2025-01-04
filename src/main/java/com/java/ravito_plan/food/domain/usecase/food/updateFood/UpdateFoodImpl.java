package com.java.ravito_plan.food.domain.usecase.food.updateFood;

import com.java.ravito_plan.food.application.mapper.FoodMapper;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateFoodImpl implements UpdateFood {

    private final FoodRepository foodRepository;

    public UpdateFoodImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional
    public void execute(UpdateFoodRequest request, UpdateFoodPresenter presenter) {
        Food food = this.foodRepository.findByIdAndBrandId(request.foodId(), request.brandId());
        food.updateFields(FoodMapper.toFood(request.command()));
        this.foodRepository.save(food);
        presenter.present(new UpdateFoodResponse(food));
    }
}
