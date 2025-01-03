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

//    @PostMapping
//    public ResponseEntity<BrandDetailView> createFood(@PathVariable("brandId") Long brandId,
//            @Valid @RequestBody CreateFoodCommand createFoodCommand) {
//        BrandDetailView brand = this.foodService.createFood(createFoodCommand);
//        return ResponseEntity.ok(brand);
//    }
//
}
