package com.java.ravito_plan.food.infrastructure.presenter.showBrand;

import com.java.ravito_plan.food.application.mapper.BrandViewMapper;
import com.java.ravito_plan.food.domain.usecase.showBrand.ShowBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.showBrand.ShowBrandResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShowBrandJsonPresenter implements ShowBrandPresenter {

    private ShowBrandModelView modelView;

    @Override
    public void present(ShowBrandResponse response) {
        this.modelView = new ShowBrandModelView(BrandViewMapper.toBrandDetailView(response.brand()));
    }
}
