package com.java.ravito_plan.food.api.brand;

import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandInterface;
import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandRequest;
import com.java.ravito_plan.food.infrastructure.presenter.brand.deleteBrand.DeleteBrandJsonPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands/{id}")
public class DeleteBrandController {

    private final DeleteBrandInterface usecase;
    private final DeleteBrandJsonPresenter presenter;

    public DeleteBrandController(DeleteBrandInterface usecase, DeleteBrandJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        this.usecase.execute(new DeleteBrandRequest(id), this.presenter);
        return ResponseEntity.noContent().build();
    }
}
