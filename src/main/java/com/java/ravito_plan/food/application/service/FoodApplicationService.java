package com.java.ravito_plan.food.application.service;

import com.java.ravito_plan.food.application.dto.command.CreateFoodCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateFoodCommand;
import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodView;
import com.java.ravito_plan.food.application.mapper.BrandViewMapper;
import com.java.ravito_plan.food.application.mapper.FoodMapper;
import com.java.ravito_plan.food.application.mapper.FoodViewMapper;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.outbound.BrandRepository;
import com.java.ravito_plan.food.domain.ports.outbound.FoodRepository;
import com.java.ravito_plan.food.domain.ports.inbound.FoodPort;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodApplicationService implements FoodPort {

    private final FoodRepository foodRepository;
    private final BrandRepository brandRepository;

    public FoodApplicationService(FoodRepository foodRepository, BrandRepository brandRepository) {
        this.foodRepository = foodRepository;
        this.brandRepository = brandRepository;
    }

    @Transactional(readOnly = true)
    public FoodDetail getFoodById(Long id) {
        return FoodMapper.toFoodDetail(this.foodRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, FoodDetail> getFoodsByIds(Collection<Long> ids) {
        return this.foodRepository.findAllByIdIn(ids.stream().toList()).stream()
                .collect(Collectors.toMap(Food::getId, FoodMapper::toFoodDetail));
    }

    @Override
    @Transactional
    public BrandDetailView createFood(CreateFoodCommand createFoodCommand) {
        Brand brand = this.brandRepository.findById(createFoodCommand.getBrandId());
        brand.addOrUpdateFood(FoodMapper.toFood(createFoodCommand));
        return BrandViewMapper.toBrandDetailView(this.brandRepository.save(brand));
    }

    @Override
    @Transactional
    public void deleteFood(Long id, Long brandId) {
        this.foodRepository.deleteByIdAndBrandId(id, brandId);
    }

    @Override
    @Transactional
    public void updateFood(UpdateFoodCommand updateFoodCommandFood) {
        Food food = this.foodRepository.findByIdAndBrandId(updateFoodCommandFood.getId(),
                updateFoodCommandFood.getBrandId());
        food.updateFields(FoodMapper.toFood(updateFoodCommandFood));

        this.foodRepository.save(food);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodSummaryView> getAllFoodsByBrand(Long brandId) {
        return this.foodRepository.findAllByBrandId(brandId).stream()
                .map(FoodViewMapper::toFoodSummaryView).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FoodView getFood(Long id, Long brandId) {
        return FoodViewMapper.toFoodView(this.foodRepository.findByIdAndBrandId(id, brandId));
    }
}
