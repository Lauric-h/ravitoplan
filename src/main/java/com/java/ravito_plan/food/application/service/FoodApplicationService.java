package com.java.ravito_plan.food.application.service;

import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.application.dto.command.CreateFoodCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateFoodCommand;
import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodView;
import com.java.ravito_plan.food.application.mapper.BrandMapper;
import com.java.ravito_plan.food.application.mapper.FoodMapper;
import com.java.ravito_plan.food.application.mapper.FoodViewMapper;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.ports.outbound.BrandRepository;
import com.java.ravito_plan.food.domain.ports.outbound.FoodRepository;
import com.java.ravito_plan.food.domain.service.FoodService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FoodApplicationService implements FoodService {

    private final FoodRepository foodRepository;
    private final BrandRepository brandRepository;

    public FoodApplicationService(FoodRepository foodRepository, BrandRepository brandRepository) {
        this.foodRepository = foodRepository;
        this.brandRepository = brandRepository;
    }

    public FoodDetail getFoodById(Long id) {
        return FoodMapper.toFoodDetail(this.foodRepository.findById(id));
    }

    @Override
    public BrandFullDto createFood(CreateFoodCommand createFoodCommand) {
        Brand brand = this.brandRepository.findById(createFoodCommand.getBrandId());
        brand.addOrUpdateFood(FoodMapper.toFood(createFoodCommand));
        return BrandMapper.toBrandFullDto(this.brandRepository.save(brand));
    }

    @Override
    public void deleteFood(Long id, Long brandId) {
        this.foodRepository.deleteByIdAndBrandId(id, brandId);
    }

    @Override
    public void updateFood(UpdateFoodCommand updateFoodCommandFood) {
        Food food = this.foodRepository.findByIdAndBrandId(updateFoodCommandFood.getId(),
                updateFoodCommandFood.getBrandId());
        food.updateFields(FoodMapper.toFood(updateFoodCommandFood));

        this.foodRepository.save(food);
    }

    @Override
    public List<FoodSummaryView> getAllFoodsByBrand(Long brandId) {
        return this.foodRepository.findAllByBrandId(brandId).stream()
                .map(FoodViewMapper::toFoodSummaryView).toList();
    }

    @Override
    public FoodView getFood(Long id, Long brandId) {
        return FoodViewMapper.toFoodView(this.foodRepository.findByIdAndBrandId(id, brandId));
    }
}
