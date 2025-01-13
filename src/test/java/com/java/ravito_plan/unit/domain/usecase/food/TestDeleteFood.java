package com.java.ravito_plan.unit.domain.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodImpl;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodRequest;
import com.java.ravito_plan.food.domain.usecase.food.deleteFood.DeleteFoodResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestDeleteFood implements DeleteFoodPresenter {

  @Autowired private InMemoryFoodRepository foodRepository;

  private DeleteFoodImpl deleteFood;
  private DeleteFoodResponse response;

  @Override
  public void present(DeleteFoodResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.foodRepository.clear();
    this.deleteFood = new DeleteFoodImpl(foodRepository);
  }

  @Test
  public void test_deleteFood() {
    Food food = new Food("Food", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    food.setId(1L);
    Brand brand = new Brand("Test Brand");
    brand.setId(1L);
    food.setBrand(brand);
    this.foodRepository.addFood(food);

    this.deleteFood.execute(new DeleteFoodRequest(1L, 1L), this);

    assertThat(this.foodRepository.findAll()).isEmpty();
  }
}
