package com.java.ravito_plan.food.infrastructure.presenter.brand.createBrand;

import com.java.ravito_plan.food.application.mapper.BrandViewMapper;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CreateBrandJsonPresenter implements CreateBrandPresenter {

    private CreateBrandViewModel viewModel;

    @Override
    public void present(CreateBrandResponse response) {
        this.viewModel = new CreateBrandViewModel(
                BrandViewMapper.toBrandSummaryView(response.brand()));
    }
}
