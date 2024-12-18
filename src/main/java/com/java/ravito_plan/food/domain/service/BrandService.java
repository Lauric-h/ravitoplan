package com.java.ravito_plan.food.domain.service;

import com.java.ravito_plan.food.application.dto.BrandDto;
import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.domain.model.Brand;
import java.util.List;

public interface BrandService {

    List<BrandDto> getAllBrands();

    BrandFullDto getById(Long id);

    BrandDto create(String name);

    void delete(Long id);

    void updateBrand(BrandDto brandDto, Long id);
}
