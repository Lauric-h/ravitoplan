package com.java.ravito_plan.unit.domain.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds.ShowFoodsByIdsImpl;
import com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds.ShowFoodsByIdsPresenter;
import com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds.ShowFoodsByIdsRequest;
import com.java.ravito_plan.food.domain.usecase.food.showFoodsByIds.ShowFoodsByIdsResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowFoodsByIds implements ShowFoodsByIdsPresenter {

  @Autowired
  private InMemoryFoodRepository foodRepository;

  private ShowFoodsByIdsImpl showFoodsByIds;

  private ShowFoodsByIdsResponse response;

  @Override
  public void present(ShowFoodsByIdsResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.foodRepository.clear();
    this.showFoodsByIds = new ShowFoodsByIdsImpl(foodRepository);
  }

  @Test
  public void test_showFoodsByIds() {
    List<Long> ids = List.of(1L, 2L, 3L);

    for (Long id : ids) {
      Food food =
          new Food(
              String.format("Food %d", id),
              10,
              20,
              30,
              false,
              "no link",
              "no comment",
              IngestionType.SOLID);
      food.setId(id);
      this.foodRepository.addFood(food);
    }

    this.showFoodsByIds.execute(new ShowFoodsByIdsRequest(ids), this);

    assertThat(this.response.foods()).isNotEmpty();
    assertThat(this.response.foods().size()).isEqualTo(ids.size());
  }

  @Test
  public void test_showFoodsByIds_returns_empty_list() {
    List<Long> ids = List.of(1L, 2L, 3L);

    Food dummyFood =  new Food(
            String.format("Food %s", 4L),
            10,
            20,
            30,
            false,
            "no link",
            "no comment",
            IngestionType.SOLID);
    dummyFood.setId(4L);

    this.foodRepository.addFood(dummyFood);

    this.showFoodsByIds.execute(new ShowFoodsByIdsRequest(ids), this);
    assertThat(this.response.foods()).isEmpty();
  }
}
