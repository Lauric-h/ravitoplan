package com.java.ravito_plan.food.domain.usecase.food.createFood;

import com.java.ravito_plan.food.application.mapper.FoodMapper;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateFood implements CreateFoodInterface {

    private final BrandRepository brandRepository;

    public CreateFood(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void execute(CreateFoodRequest request, CreateFoodPresenter presenter) {
        Brand brand = this.brandRepository.findById(request.brandId());
        Food food = FoodMapper.toFood(request.command());
        brand.addOrUpdateFood(food);
        this.brandRepository.save(brand);
        presenter.present(new CreateFoodResponse(food));
    }
}
