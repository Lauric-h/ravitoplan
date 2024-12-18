package com.java.ravito_plan.food.application.service;

import com.java.ravito_plan.food.application.dto.BrandDto;
import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.application.mapper.BrandMapper;
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

    public List<BrandDto> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream().map(BrandMapper::toBrandDto).toList();
    }

    public BrandFullDto getById(Long id) {
        return BrandMapper.toBrandFullDto(brandRepository.findById(id));
    }

    public BrandDto create(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        brandRepository.save(brand);

        return BrandMapper.toBrandDto(brand);
    }

    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    public void updateBrand(BrandDto brandDto, Long id) {
        Brand brand = brandRepository.findById(id);
        brand.updateFields(brandDto.name);
        this.brandRepository.save(brand);
    }
}
