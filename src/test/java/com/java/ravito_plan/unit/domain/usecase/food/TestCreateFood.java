package com.java.ravito_plan.unit.domain.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.dto.FoodCreationParams;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.ports.repository.FoodRepository;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodImpl;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodPresenter;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodRequest;
import com.java.ravito_plan.food.domain.usecase.food.createFood.CreateFoodResponse;
import com.java.ravito_plan.food.infrastructure.adapters.FoodFactoryImpl;
import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
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
public class TestCreateFood implements CreateFoodPresenter {

  @Autowired
  @Qualifier("inMemoryBrandRepository")
  private InMemoryBrandRepository brandRepository;

  private CreateFoodImpl createFood;

  @MockBean private FoodFactoryImpl foodFactory;

  private CreateFoodResponse response;

  @Override
  public void present(CreateFoodResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.createFood = new CreateFoodImpl(this.brandRepository, this.foodFactory);
  }

  @Test
  public void test_createFood() {
    Food expected =
        new Food("Food", 10, 20, 30, false, "no link", "no comment", IngestionType.SOLID);
    expected.setId(1L);

    Brand brand = new Brand("Test Brand");
    brand.setId(1L);
    expected.setBrand(brand);
    this.brandRepository.addBrand(brand);

    FoodCreationParams creationParams = new FoodCreationParams("Food", 10, 20, 30, false, "no link", "no comment", "SOLID");

    when(this.foodFactory.create(creationParams))
        .thenReturn(expected);

    this.createFood.execute(new CreateFoodRequest(1L, creationParams), this);

    assertThat(this.response.food()).usingRecursiveComparison().isEqualTo(expected);
  }
}
