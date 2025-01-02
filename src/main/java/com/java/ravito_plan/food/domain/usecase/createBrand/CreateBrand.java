package com.java.ravito_plan.food.domain.usecase.createBrand;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateBrand implements CreateBrandInterface {

    private final BrandRepository brandRepository;

    public CreateBrand(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void execute(CreateBrandRequest request, CreateBrandPresenter presenter) {
        Brand createdBrand = this.brandRepository.save(new Brand(request.command().getName()));
        presenter.present(new CreateBrandResponse(createdBrand));
    }
}
