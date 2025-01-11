package com.java.ravito_plan.unit.domain.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.application.exception.FoodNotFoundException;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodImpl;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodRequest;
import com.java.ravito_plan.food.domain.usecase.food.showFood.ShowFoodResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowFood implements ShowFoodPresenter {

  @Autowired
  @Qualifier("inMemoryFoodRepository")
  private InMemoryFoodRepository foodRepository;

  private ShowFoodImpl showFood;

  private ShowFoodResponse response;

  @Override
  public void present(ShowFoodResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.showFood = new ShowFoodImpl(foodRepository);
  }

  @Test
  public void test_showFood() {
    Food food = new Food("Food", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    food.setId(1L);
    this.foodRepository.addFood(food);

    this.showFood.execute(new ShowFoodRequest(1L), this);

    assertThat(this.response.food()).usingRecursiveComparison().isEqualTo(food);
  }

  @Test
  public void test_showFood_not_found_throws_exception() {
    Assertions.assertThatExceptionOfType(FoodNotFoundException.class)
        .isThrownBy(() -> this.showFood.execute(new ShowFoodRequest(1L), this));
  }
}
