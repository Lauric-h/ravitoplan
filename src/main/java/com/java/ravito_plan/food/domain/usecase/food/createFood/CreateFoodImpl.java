package com.java.ravito_plan.food.domain.usecase.food.createFood;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.FoodFactory;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateFoodImpl implements CreateFood {

    private final BrandRepository brandRepository;
    private final FoodFactory foodFactory;

    public CreateFoodImpl(BrandRepository brandRepository, FoodFactory foodFactory) {
        this.brandRepository = brandRepository;
        this.foodFactory = foodFactory;
    }

    @Override
    @Transactional
    public void execute(CreateFoodRequest request, CreateFoodPresenter presenter) {
        Brand brand = this.brandRepository.findById(request.brandId());

        Food food = this.foodFactory.create(request.foodCreationParams());

        brand.addOrUpdateFood(food);
        this.brandRepository.save(brand);

        presenter.present(new CreateFoodResponse(food));
    }
}
