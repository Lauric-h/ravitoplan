package com.java.ravito_plan.food.application.mapper;

import com.java.ravito_plan.food.application.dto.BrandDto;
import com.java.ravito_plan.food.application.dto.BrandFullDto;
import com.java.ravito_plan.food.domain.model.Brand;

public class BrandMapper {

    public static Brand toBrand(BrandDto brandDto) {
        return new Brand(brandDto.name);
    }

    public static BrandDto toBrandDto(Brand brand) {
        return new BrandDto(brand.getName());
    }

    public static BrandFullDto toBrandFullDto(Brand brand) {
        return new BrandFullDto(brand.getName(), brand.getFoods().stream().map(FoodMapper::toFoodDto).toList());
    }
}
