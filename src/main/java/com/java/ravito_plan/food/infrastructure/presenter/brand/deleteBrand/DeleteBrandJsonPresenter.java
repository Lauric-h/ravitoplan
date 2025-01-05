package com.java.ravito_plan.food.infrastructure.presenter.brand.deleteBrand;

import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DeleteBrandJsonPresenter implements DeleteBrandPresenter {

    DeleteBrandViewModel viewModel;

    @Override
    public void present(DeleteBrandResponse response) {
        this.viewModel = new DeleteBrandViewModel();
    }
}
