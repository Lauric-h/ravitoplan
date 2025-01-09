package com.java.ravito_plan.food.application.usecase.brand.updateBrand;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrand;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandRequest;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateBrandImpl implements UpdateBrand {

    private final BrandRepository brandRepository;

    public UpdateBrandImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void execute(UpdateBrandRequest request, UpdateBrandPresenter presenter) {
        Brand brand = this.brandRepository.findById(request.id());
        brand.updateFields(request.name());
        presenter.present(new UpdateBrandResponse(this.brandRepository.save(brand)));
    }
}
