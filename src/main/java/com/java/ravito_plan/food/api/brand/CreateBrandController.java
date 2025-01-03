package com.java.ravito_plan.food.api.brand;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandInterface;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandRequest;
import com.java.ravito_plan.food.infrastructure.presenter.brand.createBrand.CreateBrandJsonPresenter;
import com.java.ravito_plan.food.infrastructure.presenter.brand.createBrand.CreateBrandViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class CreateBrandController {

    private final CreateBrandInterface usecase;
    private final CreateBrandJsonPresenter presenter;

    public CreateBrandController(CreateBrandInterface usecase, CreateBrandJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @PostMapping
    public ResponseEntity<CreateBrandViewModel> createRace(
            @Valid @RequestBody CreateBrandCommand command) {
        this.usecase.execute(new CreateBrandRequest(command), this.presenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.presenter.getViewModel());
    }
}
