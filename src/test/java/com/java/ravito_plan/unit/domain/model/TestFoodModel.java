package com.java.ravito_plan.unit.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestFoodModel {

  @Test
  public void test_updateFields() {
    Food food =
        new Food("Before update", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);

    food.updateFields("After update", 100, 200, 300, true, "link", "comment", IngestionType.LIQUID);

    assertThat(food.getName()).isEqualTo("After update");
    assertThat(food.getCarbohydrates()).isEqualTo(100);
    assertThat(food.getCalories()).isEqualTo(200);
    assertThat(food.getProteins()).isEqualTo(300);
    assertThat(food.isElectrolytes()).isTrue();
    assertThat(food.getLink()).isEqualTo("link");
    assertThat(food.getComment()).isEqualTo("comment");
    assertThat(food.getIngestionType()).isEqualTo(IngestionType.LIQUID);
  }
}
