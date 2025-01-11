package com.java.ravito_plan.unit.domain.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.dto.FoodUpdateParams;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.ports.FoodFactory;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFoodImpl;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFoodRequest;
import com.java.ravito_plan.food.domain.usecase.food.updateFood.UpdateFoodResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestUpdateFood implements UpdateFoodPresenter {

  @Autowired
  @Qualifier("inMemoryFoodRepository")
  private InMemoryFoodRepository foodRepository;

  private UpdateFoodImpl updateFood;

  @MockBean private FoodFactory foodFactory;

  private UpdateFoodResponse response;

  @Override
  public void present(UpdateFoodResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.updateFood = new UpdateFoodImpl(this.foodRepository, this.foodFactory);
  }

  @Test
  public void test_updateFood() {
    Food food = new Food("Food", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    food.setId(1L);
    Brand brand = new Brand("Test Brand");
    brand.setId(1L);
    food.setBrand(brand);
    this.foodRepository.addFood(food);

    FoodUpdateParams params =
        new FoodUpdateParams(
            "Updated Food", 100, 200, 300, false, "link", "comment", "SEMI_LIQUID");

    Food expected =
        new Food(
            "Updated Food", 100, 200, 300, false, "link", "comment", IngestionType.SEMI_LIQUID);
    expected.setBrand(brand);
    expected.setId(1L);

    when(this.foodFactory.updateFields(food, params)).thenReturn(expected);

    this.updateFood.execute(new UpdateFoodRequest(1L, 1L, params), this);

    assertThat(this.response.food()).usingRecursiveComparison().isEqualTo(expected);
  }
}
