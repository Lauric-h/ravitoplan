package com.java.ravito_plan.food.application.service;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.BrandSummaryView;
import com.java.ravito_plan.food.application.mapper.BrandViewMapper;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.outbound.BrandRepository;
import com.java.ravito_plan.food.domain.service.BrandService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BrandApplicationService implements BrandService {

    private final BrandRepository brandRepository;

    public BrandApplicationService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandSummaryView> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream().map(BrandViewMapper::toBrandSummaryView).toList();
    }

    public BrandDetailView getBrandById(Long id) {
        return BrandViewMapper.toBrandDetailView(brandRepository.findById(id));
    }

    public BrandDetailView createBrand(CreateBrandCommand command) {
        Brand brand = new Brand();
        brand.setName(command.getName());
        brandRepository.save(brand);

        return  BrandViewMapper.toBrandDetailView(brand);
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public BrandDetailView updateBrand(UpdateBrandCommand command) {
        Brand brand = brandRepository.findById(command.getId());
        brand.updateFields(command.getName());
        return BrandViewMapper.toBrandDetailView(this.brandRepository.save(brand));
    }
}
