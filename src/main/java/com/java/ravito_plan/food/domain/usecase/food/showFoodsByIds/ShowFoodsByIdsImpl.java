package com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds;

import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowFoodsByIdsImpl implements ShowFoodsByIds {

    private final FoodRepository foodRepository;

    public ShowFoodsByIdsImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowFoodsByIdsRequest request, ShowFoodsByIdsPresenter presenter) {
        List<Food> foods = this.foodRepository.findAllByIdIn(request.ids().stream().toList());
        presenter.present(new ShowFoodsByIdsResponse(foods));
    }
}
