package com.java.ravito_plan.food.domain.service;

import com.java.ravito_plan.food.application.dto.BrandDto;
import com.java.ravito_plan.food.application.dto.BrandFullDto;
import java.util.List;

public interface BrandService {

    List<BrandDto> getAllBrands();

    BrandFullDto getBrandById(Long id);

    BrandDto createBrand(String name);

    void deleteBrand(Long id);

    void updateBrand(BrandDto brandDto, Long id);
}
