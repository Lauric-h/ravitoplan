package com.java.ravito_plan.food.domain.usecase.food.showAllFoods;

import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowAllFoodsImpl implements ShowAllFoods {

    private final FoodRepository foodRepository;

    public ShowAllFoodsImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowAllFoodsRequest request, ShowAllFoodsPresenter presenter) {
        List<Food> foods = this.foodRepository.findAllByBrandId(request.brandId());
        presenter.present(new ShowAllFoodsResponse(foods));
    }
}
