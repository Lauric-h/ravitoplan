package com.java.ravito_plan.food.application.mapper;

import com.java.ravito_plan.food.application.dto.internal.BrandDetail;
import com.java.ravito_plan.food.domain.model.Brand;

public class BrandMapper {

    public static Brand toBrand(BrandDetail brandDetail) {
        return new Brand(brandDetail.name);
    }

    public static BrandDetail toBrandDetail(Brand brand) {
        return new BrandDetail(brand.getId(), brand.getName());
    }
}
