package com.java.ravito_plan.unit.domain.usecase.race.race;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.application.exception.RaceNotFoundException;
import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRaceImpl;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRaceResponse;
import java.time.LocalDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowFullRace implements ShowFullRacePresenter {

  @MockBean FoodPort foodPort;
  @Autowired private InMemoryRaceRepository raceRepository;
  private ShowFullRaceImpl showFullRace;

  private ShowFullRaceResponse response;

  @Override
  public void present(ShowFullRaceResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.showFullRace = new ShowFullRaceImpl(raceRepository, foodPort);
  }

  @Test
  public void test_showFullRace_not_found_throws_exception() {
    Assertions.assertThatExceptionOfType(RaceNotFoundException.class)
        .isThrownBy(() -> this.showFullRace.execute(new ShowFullRaceRequest(1L, 1L), this));
  }

  @Test
  public void test_showFullRace() {
    Race race =
        new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    Long userId = 2L;
    race.setUserId(userId);
    race.setId(1L);
    this.raceRepository.addRace(race);

    CheckpointFood cpf1 = new CheckpointFood(race.getStartCheckpoint(), 1, 1L);
    CheckpointFood cpf2 = new CheckpointFood(race.getStartCheckpoint(), 1, 2L);
    CheckpointFood cpf3 = new CheckpointFood(race.getStartCheckpoint(), 1, 3L);

    race.getStartCheckpoint().addFood(cpf1);
    race.getStartCheckpoint().addFood(cpf2);
    race.getStartCheckpoint().addFood(cpf3);

    Map<Long, RaceFoodDto> foods =
        Map.of(
            1L,
                new RaceFoodDto(
                    1L,
                    "Test Brand",
                    "Test Food",
                    100,
                    100,
                    100,
                    false,
                    "link",
                    "comment",
                    IngestionType.SOLID.name()),
            2L,
                new RaceFoodDto(
                    1L,
                    "Test Brand 2",
                    "Test Food2",
                    200,
                    200,
                    200,
                    false,
                    "link",
                    "comment",
                    IngestionType.SOLID.name()),
            3L,
                new RaceFoodDto(
                    1L,
                    "Test Brand 3",
                    "Test Food3",
                    300,
                    300,
                    300,
                    false,
                    "link",
                    "comment",
                    IngestionType.SOLID.name()));

    when(this.foodPort.getFoodsByIds(race.getAllFoodIds())).thenReturn(foods);

    this.showFullRace.execute(new ShowFullRaceRequest(1L, 2L), this);

    assertThat(this.response.race()).usingRecursiveComparison().isEqualTo(race);
    assertThat(this.response.foods()).usingRecursiveComparison().isEqualTo(foods);
    assertThat(this.response.foods().size()).isEqualTo(race.getAllFoodIds().size());
  }
}
