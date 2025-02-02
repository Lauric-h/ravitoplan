package com.java.ravito_plan.food.infrastructure.presenter.brand.updateBrand;

import com.java.ravito_plan.food.application.mapper.BrandViewMapper;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UpdateBrandJsonPresenter implements UpdateBrandPresenter {

    UpdateBrandViewModel viewModel;

    @Override
    public void present(UpdateBrandResponse response) {
        this.viewModel = new UpdateBrandViewModel(BrandViewMapper.toBrandDetailView(response.brand()));
    }
}
