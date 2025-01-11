package com.java.ravito_plan.food.domain.usecase.brand.updateBrand;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
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
        Brand updatedBrand = this.brandRepository.save(brand.updateFields(request.name()));
        presenter.present(new UpdateBrandResponse(updatedBrand));
    }
}
