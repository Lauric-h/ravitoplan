package com.java.ravito_plan.food.domain.usecase.deleteBrand;

import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteBrand implements DeleteBrandInterface {

    private final BrandRepository brandRepository;

    public DeleteBrand(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void execute(DeleteBrandRequest request, DeleteBrandPresenter presenter) {
        this.brandRepository.deleteById(request.id());
    }
}
