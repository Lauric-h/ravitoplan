package com.java.ravito_plan.food.api.brand;

import com.java.ravito_plan.food.domain.usecase.showAllBrands.ShowAllBrandsInterface;
import com.java.ravito_plan.food.domain.usecase.showAllBrands.ShowAllBrandsRequest;
import com.java.ravito_plan.food.infrastructure.presenter.showAllBrands.ShowAllBrandsJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.showAllBrands.ShowAllBrandsViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class ShowAllBrandsController {

    private final ShowAllBrandsInterface usecase;
    private final ShowAllBrandsJsonPresenter presenter;

    public ShowAllBrandsController(ShowAllBrandsInterface usecase,
            ShowAllBrandsJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @GetMapping
    public ResponseEntity<ShowAllBrandsViewModel> showAllBrands() {
        this.usecase.execute(new ShowAllBrandsRequest(), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
