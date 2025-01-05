package com.java.ravito_plan.food.api.food;

import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoods;
import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoodsRequest;
import com.java.ravito_plan.food.infrastructure.presenter.food.showAllFoods.ShowAllFoodsJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.food.showAllFoods.ShowAllFoodsViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{brandId}/foods")
public class ShowAllFoodsController {

    private final ShowAllFoods usecase;
    private final ShowAllFoodsJsonPresenter presenter;

    public ShowAllFoodsController(ShowAllFoods usecase,
            ShowAllFoodsJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @GetMapping
    public ResponseEntity<ShowAllFoodsViewModel> showAllFoods(@PathVariable Long brandId) {
        this.usecase.execute(new ShowAllFoodsRequest(brandId), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
