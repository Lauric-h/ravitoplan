package com.java.ravito_plan.unit.domain.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoodsImpl;
import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoodsPresenter;
import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoodsRequest;
import com.java.ravito_plan.food.domain.usecase.food.showAllFoods.ShowAllFoodsResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowAllFoods implements ShowAllFoodsPresenter {

  @Autowired
  @Qualifier("inMemoryFoodRepository")
  private InMemoryFoodRepository foodRepository;

  private ShowAllFoodsImpl showAllFoods;

  private ShowAllFoodsResponse response;

  @BeforeEach
  public void setUp() {
    this.showAllFoods = new ShowAllFoodsImpl(foodRepository);
  }

  @Override
  public void present(ShowAllFoodsResponse response) {
    this.response = response;
  }

  @Test
  public void test_showAllFoods() {
    Food food1 = new Food("Food1", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    Food food2 = new Food("Food2", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    food1.setId(1L);
    food2.setId(2L);
    Brand brand = new Brand("Test Brand");
    brand.setId(1L);
    food1.setBrand(brand);
    food2.setBrand(brand);
    this.foodRepository.addFood(food1);
    this.foodRepository.addFood(food2);

    this.showAllFoods.execute(new ShowAllFoodsRequest(1L), this);

    assertThat(this.response.foods().size()).isEqualTo(2);
  }

  @Test
  public void test_showAllFoods_brandId_not_found() {
    Food food1 = new Food("Food1", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    Food food2 = new Food("Food2", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    food1.setId(1L);
    food2.setId(2L);
    Brand brand = new Brand("Test Brand");
    brand.setId(1L);
    food1.setBrand(brand);
    food2.setBrand(brand);
    this.foodRepository.addFood(food1);
    this.foodRepository.addFood(food2);

    this.showAllFoods.execute(new ShowAllFoodsRequest(2L), this);

    assertThat(this.response.foods()).isEmpty();
  }
}
