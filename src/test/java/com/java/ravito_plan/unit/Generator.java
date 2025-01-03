package com.java.ravito_plan.unit;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;
import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.BrandSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodView;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import java.util.List;

public class Generator {

    public static CreateBrandCommand getCreateBrandCommand(String name) {
        return new CreateBrandCommand(name);
    }

    public static UpdateBrandCommand getUpdateBrandCommand(String name) {
        return new UpdateBrandCommand(name);
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

    public static FoodView getFoodView(String name,
            int carbohydrates, int calories, int proteins, boolean electrolytes, String link,
            String comment, String ingestionType) {
        return new FoodView( name, carbohydrates, calories, proteins, electrolytes,
                link, comment, ingestionType);
    }

    public static FoodDetail getFoodDetail(Long id, String brandName, String name,
            int carbohydrates, int calories, int proteins, boolean electrolytes, String link,
            String comment, String ingestionType) {
        return new FoodDetail(id, brandName, name, carbohydrates, calories, proteins, electrolytes,
                link, comment, ingestionType);
    }

    public static FoodDetail getPopulatedFoodDetail() {
        return Generator.getFoodDetail(1L, "Brand name", "Food name", 10, 11, 12, false, "link",
                "comment", "LIQUID");
    }

    public static Food getFood(Long id, String name, int carbohydrates, int calories, int proteins,
            boolean electrolytes, String link, String comment, IngestionType ingestionType) {
        Food food = new Food(name, carbohydrates, calories, proteins, electrolytes, link, comment,
                ingestionType);
        food.setId(id);
        food.setBrand(Generator.getBrand(1L, "Brand name", List.of(food)));

        return food;
    }

    public static Food getPopulatedFood() {
        return Generator.getFood(1L, "Food name", 10, 11, 12, false, "link", "comment",
                IngestionType.LIQUID);
    }
}
