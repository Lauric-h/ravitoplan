package com.java.ravito_plan.food.api.brand;

import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrand;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandRequest;
import com.java.ravito_plan.food.infrastructure.presenter.brand.updateBrand.UpdateBrandJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.brand.updateBrand.UpdateBrandViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{id}")
public class UpdateBrandController {

    private final UpdateBrand usecase;
    private final UpdateBrandJsonPresenter presenter;

    public UpdateBrandController(UpdateBrand updateBrand,
            UpdateBrandJsonPresenter presenter) {
        this.usecase = updateBrand;
        this.presenter = presenter;
    }

    @PutMapping
    public ResponseEntity<UpdateBrandViewModel> updateBrand(@PathVariable Long id, @Valid @RequestBody
            UpdateBrandCommand command) {
        this.usecase.execute(new UpdateBrandRequest(id, command.getName()), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
