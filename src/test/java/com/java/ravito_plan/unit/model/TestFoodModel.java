package com.java.ravito_plan.unit.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.unit.Generator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestFoodModel {

//    @Test
//    public void testUpdateFields() {
//        Food food = Generator.getFood(1L, "Food name", 10, 11, 12, false, "link", "comment",
//                IngestionType.LIQUID);
//        Food updatedFood = Generator.getFood(1L, "Updated Food name", 20, 21, 22, true, "updated link", "updated comment",
//                IngestionType.SOLID);
//        Food expectedFood = Generator.getFood(1L, "Updated Food name", 20, 21, 22, true, "updated link", "updated comment",
//                IngestionType.SOLID);
//
//        Food actual = food.updateFields(updatedFood);
//
//        assertThat(actual.getName()).isEqualTo(expectedFood.getName());
//        assertThat(actual.getCarbohydrates()).isEqualTo(expectedFood.getCarbohydrates());
//        assertThat(actual.getCalories()).isEqualTo(expectedFood.getCalories());
//        assertThat(actual.getProteins()).isEqualTo(expectedFood.getProteins());
//        assertThat(actual.isElectrolytes()).isEqualTo(expectedFood.isElectrolytes());
//        assertThat(actual.getLink()).isEqualTo(expectedFood.getLink());
//        assertThat(actual.getComment()).isEqualTo(expectedFood.getComment());
//        assertThat(actual.getIngestionType()).isEqualTo(expectedFood.getIngestionType());
//    }
}
