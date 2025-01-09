package com.java.ravito_plan.food.application.usecase.brand.showAllBrands;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrands;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrandsPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrandsRequest;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrandsResponse;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowAllBrandsImpl implements ShowAllBrands {

    private final BrandRepository brandRepository;

    public ShowAllBrandsImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowAllBrandsRequest request, ShowAllBrandsPresenter presenter) {
        List<Brand> brands = this.brandRepository.findAll();
        presenter.present(new ShowAllBrandsResponse(brands));
    }
}
