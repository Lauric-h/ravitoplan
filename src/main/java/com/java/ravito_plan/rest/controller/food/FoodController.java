package com.java.ravito_plan.rest.controller.food;

import com.java.ravito_plan.food.application.dto.command.CreateFoodCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateFoodCommand;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodView;
import com.java.ravito_plan.food.application.service.FoodApplicationService;
import jakarta.validation.Valid;
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

    private final FoodApplicationService foodService;

    public FoodController(FoodApplicationService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<FoodSummaryView>> getAllFoodsByBrand(@PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(this.foodService.getAllFoodsByBrand(brandId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodView> getFoodById(@PathVariable("id") Long id,
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
    public ResponseEntity<BrandDetailView> createFood(@PathVariable("brandId") Long brandId,
            @Valid @RequestBody CreateFoodCommand createFoodCommand) {
        BrandDetailView brand = this.foodService.createFood(createFoodCommand);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFood(@PathVariable("brandId") Long brandId,
            @PathVariable("id") Long id, @Valid @RequestBody UpdateFoodCommand updateFoodCommand) {
        this.foodService.updateFood(updateFoodCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
