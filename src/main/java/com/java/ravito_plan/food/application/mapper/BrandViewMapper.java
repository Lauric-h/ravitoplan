package com.java.ravito_plan.food.application.mapper;

import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.BrandSummaryView;
import com.java.ravito_plan.food.domain.model.Brand;

public class BrandViewMapper {

    public static BrandSummaryView toBrandSummaryView(Brand brand) {
        return new BrandSummaryView(brand.getName());
    }

    public static BrandDetailView toBrandDetailView(Brand brand) {
        return new BrandDetailView(brand.getName(),
                brand.getFoods().stream().map(FoodViewMapper::toFoodSummaryView).toList());
    }
}
