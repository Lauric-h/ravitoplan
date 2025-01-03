package com.java.ravito_plan.food.domain.usecase.updateBrand;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateBrand implements UpdateBrandInterface {

    private final BrandRepository brandRepository;

    public UpdateBrand(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void execute(UpdateBrandRequest request, UpdateBrandPresenter presenter) {
        Brand brand = this.brandRepository.findById(request.id());
        brand.updateFields(request.command().getName());
        presenter.present(new UpdateBrandResponse(this.brandRepository.save(brand)));
    }
}
