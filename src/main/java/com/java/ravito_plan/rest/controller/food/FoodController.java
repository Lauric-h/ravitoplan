package com.java.ravito_plan.rest.controller.food;

import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.application.dto.FoodDto;
import com.java.ravito_plan.food.domain.service.FoodService;
import com.java.ravito_plan.rest.view.food.FoodRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{brandId}/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<FoodDto>> getAllFoodsByBrand(@PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(this.foodService.getAllFoodsByBrand(brandId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDto> getFoodById(@PathVariable("id") Long id,
            @PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(this.foodService.getFood(id, brandId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") Long id,
            @PathVariable("brandId") Long brandId) {
        this.foodService.deleteFood(id, brandId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BrandFullDto> createFood(@PathVariable("brandId") Long brandId, @RequestBody FoodRequest foodRequest) {
        BrandFullDto brand = this.foodService.createFood(
                new FoodDto(foodRequest.name, foodRequest.carbohydrates, foodRequest.calories,
                        foodRequest.proteins, foodRequest.electrolytes, foodRequest.link,
                        foodRequest.comment, foodRequest.type), brandId);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("/{id}")
   public ResponseEntity<Void> updateFood(@PathVariable("brandId") Long brandId, @PathVariable("id") Long id, @RequestBody FoodRequest foodRequest) {
        this.foodService.updateFood( new FoodDto(foodRequest.name, foodRequest.carbohydrates, foodRequest.calories,
                foodRequest.proteins, foodRequest.electrolytes, foodRequest.link,
                foodRequest.comment, foodRequest.type), id, brandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
