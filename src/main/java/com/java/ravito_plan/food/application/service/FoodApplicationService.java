package com.java.ravito_plan.food.application.service;

import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.application.dto.FoodDto;
import com.java.ravito_plan.food.application.mapper.BrandMapper;
import com.java.ravito_plan.food.application.mapper.FoodMapper;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
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
    public BrandFullDto createFood(FoodDto foodDto, Long brandId) {
        Brand brand = this.brandRepository.findById(brandId);
        brand.addOrUpdateFood(FoodMapper.toFood(foodDto));
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
