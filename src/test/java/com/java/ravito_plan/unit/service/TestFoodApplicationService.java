package com.java.ravito_plan.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.application.dto.view.FoodView;
import com.java.ravito_plan.food.application.service.FoodApplicationService;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import com.java.ravito_plan.unit.Generator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestFoodApplicationService {

    @Autowired
    private FoodApplicationService foodApplicationService;

    @MockBean
    private FoodRepository foodRepository;

    @MockBean
    private BrandRepository brandRepository;

//    @Test
//    public void testGetFoodById() {
//        FoodDetail expected = Generator.getPopulatedFoodDetail();
//        Food food = Generator.getPopulatedFood();
//
//        when(this.foodRepository.findById(1L)).thenReturn(food);
//
//        FoodDetail actual = this.foodApplicationService.getFoodById(1L);
//
//        assertThat(actual.id).isEqualTo(expected.id);
//        assertThat(actual.name).isEqualTo(expected.name);
//        assertThat(actual.brandName).isEqualTo(expected.brandName);
//        assertThat(actual.name).isEqualTo(expected.name);
//        assertThat(actual.carbohydrates).isEqualTo(expected.carbohydrates);
//        assertThat(actual.calories).isEqualTo(expected.calories);
//        assertThat(actual.proteins).isEqualTo(expected.proteins);
//        assertThat(actual.electrolytes).isEqualTo(expected.electrolytes);
//        assertThat(actual.link).isEqualTo(expected.link);
//        assertThat(actual.comment).isEqualTo(expected.comment);
//        assertThat(actual.ingestionType).isEqualTo(expected.ingestionType);
//    }
//
//    @Test
//    public void testGetFood() {
//        FoodView expected = Generator.getFoodView("Food name", 10, 11, 12, false, "link",
//                "comment", "LIQUID");
//        Food food = Generator.getPopulatedFood();
//        Brand brand = Generator.getBrand(2L, "Brand name", List.of(food));
//        food.setBrand(brand);
//
//
//        when(this.foodRepository.findByIdAndBrandId(1L, 2L)).thenReturn(food);
//
//        FoodView actual = this.foodApplicationService.getFood(1L, 2L);
//
//        assertThat(actual.name).isEqualTo(expected.name);
//        assertThat(actual.name).isEqualTo(expected.name);
//        assertThat(actual.carbohydrates).isEqualTo(expected.carbohydrates);
//        assertThat(actual.calories).isEqualTo(expected.calories);
//        assertThat(actual.proteins).isEqualTo(expected.proteins);
//        assertThat(actual.electrolytes).isEqualTo(expected.electrolytes);
//        assertThat(actual.link).isEqualTo(expected.link);
//        assertThat(actual.comment).isEqualTo(expected.comment);
//        assertThat(actual.ingestionType).isEqualTo(expected.ingestionType);
//    }

}
