package com.java.ravito_plan.food.api.brand;

import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrand;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandRequest;
import com.java.ravito_plan.food.infrastructure.presenter.brand.showBrand.ShowBrandJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.brand.showBrand.ShowBrandViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{id}")
public class ShowBrandController {

    private final ShowBrand usecase;
    private final ShowBrandJsonPresenter presenter;

    public ShowBrandController(ShowBrand usecase, ShowBrandJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @GetMapping
    public ResponseEntity<ShowBrandViewModel> showBrand(@PathVariable Long id) {
        this.usecase.execute(new ShowBrandRequest(id), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
