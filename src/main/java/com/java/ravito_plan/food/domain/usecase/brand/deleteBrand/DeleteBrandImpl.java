package com.java.ravito_plan.food.domain.usecase.brand.deleteBrand;

import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteBrandImpl implements DeleteBrand {

    private final BrandRepository brandRepository;

    public DeleteBrandImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void execute(DeleteBrandRequest request, DeleteBrandPresenter presenter) {
        this.brandRepository.deleteById(request.id());
        presenter.present(new DeleteBrandResponse());
    }
}
