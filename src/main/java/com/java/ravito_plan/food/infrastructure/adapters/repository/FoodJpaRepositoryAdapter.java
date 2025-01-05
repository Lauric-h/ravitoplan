package com.java.ravito_plan.food.infrastructure.adapters.repository;

import com.java.ravito_plan.food.application.exception.FoodNotFoundException;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FoodJpaRepositoryAdapter implements FoodRepository {

    private final ImportedFoodRepository foodRepository;
    public FoodJpaRepositoryAdapter(ImportedFoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food findById(Long id) {
        return this.foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException(id));
    }

    @Override
    public List<Food> findAllByIdIn(List<Long> ids) {
        return this.foodRepository.findAllById(ids);
    }

    @Override
    public Food findByIdAndBrandId(Long id, Long brandId) {
        return this.foodRepository.findByIdAndBrandId(id, brandId).orElseThrow(() -> new FoodNotFoundException(id));
    }

    @Override
    public Food save(Food food) {
        return this.foodRepository.save(food);
    }

    @Override
    public List<Food> findAllByBrandId(Long brandId) {
        return this.foodRepository.findAllByBrandId(brandId);
    }

    @Override
    @Transactional
    public void deleteByIdAndBrandId(Long id, Long brandId) {
        this.foodRepository.deleteByIdAndBrandId(id, brandId);
    }
}
