package com.java.ravito_plan.unit;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.BrandSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import java.util.List;

public class Generator {

    public static CreateBrandCommand getCreateBrandCommand(String name) {
        return new CreateBrandCommand(name);
    }

    public static UpdateBrandCommand getUpdateBrandCommand(Long id, String name) {
        return new UpdateBrandCommand(id, name);
    }

    public static BrandDetailView getBrandDetailView(String name, List<FoodSummaryView> foods) {
        return new BrandDetailView(name, foods);
    }

    public static BrandSummaryView getBrandSummaryView(String name) {
        return new BrandSummaryView(name);
    }

    public static Brand getBrand(Long id, String name, List<Food> foods) {
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(name);
        brand.setFoods(foods);
        return brand;
    }

}
