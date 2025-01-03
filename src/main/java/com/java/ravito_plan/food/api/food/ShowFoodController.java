package com.java.ravito_plan.food.api.food;

import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodInterface;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodRequest;
import com.java.ravito_plan.food.infrastructure.presenter.food.showFood.ShowFoodJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.food.showFood.ShowFoodViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{brandId}/foods")
public class ShowFoodController {

    private final ShowFoodInterface usecase;
    private final ShowFoodJsonPresenter presenter;

    public ShowFoodController(ShowFoodInterface usecase, ShowFoodJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<ShowFoodViewModel> getFood(@PathVariable Long brandId, @PathVariable Long foodId) {
        this.usecase.execute(new ShowFoodRequest(foodId, brandId), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
