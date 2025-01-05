package com.java.ravito_plan.food.infrastructure.presenter.brand.showBrand;

import com.java.ravito_plan.food.application.mapper.BrandViewMapper;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShowBrandJsonPresenter implements ShowBrandPresenter {

    private ShowBrandViewModel viewModel;

    @Override
    public void present(ShowBrandResponse response) {
        this.viewModel = new ShowBrandViewModel(
                BrandViewMapper.toBrandDetailView(response.brand()));
    }
}
