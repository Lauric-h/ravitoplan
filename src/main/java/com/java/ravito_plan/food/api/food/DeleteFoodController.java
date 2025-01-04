package com.java.ravito_plan.food.api.food;

import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFood;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodRequest;
import com.java.ravito_plan.food.infrastructure.presenter.food.deleteFood.DeleteFoodJsonPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{brandId}/foods/{foodId}")
public class DeleteFoodController {

    private final DeleteFood usecase;
    private final DeleteFoodJsonPresenter presenter;

    public DeleteFoodController(DeleteFood usecase, DeleteFoodJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFood(@PathVariable Long brandId, @PathVariable Long foodId) {
        this.usecase.execute(new DeleteFoodRequest(foodId, brandId), this.presenter);
        return ResponseEntity.noContent().build();
    }
}
