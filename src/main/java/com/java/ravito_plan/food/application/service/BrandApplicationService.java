package com.java.ravito_plan.food.application.service;

import com.java.ravito_plan.food.application.dto.BrandDto;
import com.java.ravito_plan.food.application.mapper.BrandMapper;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.outbound.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandApplicationService {

    private final BrandRepository brandRepository;

    public BrandApplicationService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandDto getById(Long id) {
        return BrandMapper.toBrandDto(brandRepository.findById(id));
    }

    public Brand save(BrandDto brandDto) {
        return brandRepository.save(BrandMapper.toBrand(brandDto));
    }
}
