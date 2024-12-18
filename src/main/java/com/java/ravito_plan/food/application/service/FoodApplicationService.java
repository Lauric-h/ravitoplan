package com.java.ravito_plan.food.application.service;

import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.application.dto.FoodDto;
import com.java.ravito_plan.food.application.mapper.BrandMapper;
import com.java.ravito_plan.food.application.mapper.FoodMapper;
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

    public FoodDto getFoodById(Long id) {
        return FoodMapper.toFoodDto(this.foodRepository.findById(id));
    }

    @Override
    public List<FoodDto> getAllFoodsByBrand(Long brandId) {
        return this.foodRepository.findAllByBrandId(brandId).stream().map(FoodMapper::toFoodDto)
                .toList();
    }

    @Override
    public FoodDto getFood(Long id, Long brandId) {
        return FoodMapper.toFoodDto(this.foodRepository.findByIdAndBrandId(id, brandId));
    }

    @Override
    public BrandFullDto createFood(String name, int carbohydrates, int calories, int proteins,
            boolean electrolytes, String link, String comment, String type, Long brandId) {
        Brand brand = this.brandRepository.findById(brandId);
        brand.addOrUpdateFood(
                new Food(name, carbohydrates, calories, proteins, electrolytes, link, comment,
                        IngestionType.valueOf(type)));
        return BrandMapper.toBrandFullDto(this.brandRepository.save(brand));
    }

    @Override
    public void deleteFood(Long id, Long brandId) {
        this.foodRepository.deleteByIdAndBrandId(id, brandId);
    }

    @Override
    public void updateFood(FoodDto foodDto, Long foodId, Long brandId) {

        Food food = this.foodRepository.findByIdAndBrandId(foodId, brandId);
        food.updateFields(FoodMapper.toFood(foodDto));

        this.foodRepository.save(food);
    }
}
