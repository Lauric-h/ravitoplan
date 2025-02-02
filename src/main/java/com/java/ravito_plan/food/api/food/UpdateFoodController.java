package com.java.ravito_plan.food.api.food;

import com.java.ravito_plan.food.application.dto.command.UpdateFoodCommand;
import com.java.ravito_plan.food.application.mapper.FoodMapper;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFood;
import com.java.ravito_plan.food.infrastructure.presenter.food.updateFood.UpdateFoodJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.food.updateFood.UpdateFoodViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{brandId}/foods/{foodId}")
public class UpdateFoodController {

    private final UpdateFood usecase;
    private final UpdateFoodJsonPresenter presenter;

    public UpdateFoodController(UpdateFood usecase, UpdateFoodJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @PutMapping
    public ResponseEntity<UpdateFoodViewModel> updateFood(@PathVariable Long brandId,
            @PathVariable Long foodId, @Valid @RequestBody UpdateFoodCommand command) {
        this.usecase.execute(FoodMapper.toUpdateFoodRequest(foodId, brandId, command), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
