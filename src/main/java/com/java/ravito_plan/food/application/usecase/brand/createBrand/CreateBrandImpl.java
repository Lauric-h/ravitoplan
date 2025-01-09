package com.java.ravito_plan.food.application.usecase.brand.createBrand;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrand;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandRequest;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateBrandImpl implements CreateBrand {

    private final BrandRepository brandRepository;

    public CreateBrandImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void execute(CreateBrandRequest request, CreateBrandPresenter presenter) {
        Brand createdBrand = this.brandRepository.save(new Brand(request.name()));
        presenter.present(new CreateBrandResponse(createdBrand));
    }
}
