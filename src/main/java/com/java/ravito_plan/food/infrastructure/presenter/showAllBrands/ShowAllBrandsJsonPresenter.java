package com.java.ravito_plan.food.infrastructure.presenter.showAllBrands;

import com.java.ravito_plan.food.application.mapper.BrandViewMapper;
import com.java.ravito_plan.food.domain.usecase.showAllBrands.ShowAllBrandsPresenter;
import com.java.ravito_plan.food.domain.usecase.showAllBrands.ShowAllBrandsResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShowAllBrandsJsonPresenter implements ShowAllBrandsPresenter {

    private ShowAllBrandsViewModel viewModel;

    @Override
    public void present(ShowAllBrandsResponse response) {
        this.viewModel = new ShowAllBrandsViewModel(
                response.brands().stream().map(BrandViewMapper::toBrandSummaryView).toList());
    }
}
