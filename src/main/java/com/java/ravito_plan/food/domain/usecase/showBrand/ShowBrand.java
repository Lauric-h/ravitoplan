package com.java.ravito_plan.food.domain.usecase.showBrand;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowBrand implements ShowBrandInterface {

    private final BrandRepository brandRepository;

    public ShowBrand(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowBrandRequest request, ShowBrandPresenter presenter) {
        Brand brand = this.brandRepository.findById(request.id());
        presenter.present(new ShowBrandResponse(brand));
    }
}
