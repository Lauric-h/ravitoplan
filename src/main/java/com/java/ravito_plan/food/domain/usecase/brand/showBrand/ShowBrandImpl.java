package com.java.ravito_plan.food.domain.usecase.brand.showBrand;

import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowBrandImpl implements ShowBrand {

    private final BrandRepository brandRepository;

    public ShowBrandImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowBrandRequest request, ShowBrandPresenter presenter) {
        Brand brand = this.brandRepository.findById(request.id());
        presenter.present(new ShowBrandResponse(brand));
    }
}
