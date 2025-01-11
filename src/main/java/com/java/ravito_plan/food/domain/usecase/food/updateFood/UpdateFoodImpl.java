package com.java.ravito_plan.food.domain.usecase.food.updateFood;

import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.FoodFactory;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateFoodImpl implements UpdateFood {

    private final FoodRepository foodRepository;
    private final FoodFactory foodFactory;

    public UpdateFoodImpl(FoodRepository foodRepository, FoodFactory foodFactory) {
        this.foodRepository = foodRepository;
        this.foodFactory = foodFactory;
    }

    @Override
    @Transactional
    public void execute(UpdateFoodRequest request, UpdateFoodPresenter presenter) {
        Food food = this.foodRepository.findByIdAndBrandId(request.foodId(), request.brandId());
        Food updatedFood = this.foodRepository.save(this.foodFactory.updateFields(food, request.foodUpdateParams()));
        presenter.present(new UpdateFoodResponse(updatedFood));
    }
}
