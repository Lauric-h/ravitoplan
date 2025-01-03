package com.java.ravito_plan.food.api.food;

import com.java.ravito_plan.food.application.dto.command.CreateFoodCommand;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodInterface;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodRequest;
import com.java.ravito_plan.food.infrastructure.presenter.food.createFood.CreateFoodJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.food.createFood.CreateFoodViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{brandId}/foods")
public class CreateFoodController {

    private final CreateFoodInterface usecase;
    private final CreateFoodJsonPresenter presenter;

    public CreateFoodController(CreateFoodInterface usecase, CreateFoodJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @PostMapping
    public ResponseEntity<CreateFoodViewModel> createFood(@PathVariable Long brandId,
            @RequestBody CreateFoodCommand command) {
        this.usecase.execute(new CreateFoodRequest(brandId, command), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
