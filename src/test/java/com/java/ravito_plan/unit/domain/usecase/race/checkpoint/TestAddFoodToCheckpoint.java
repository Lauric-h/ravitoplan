package com.java.ravito_plan.unit.domain.usecase.race.checkpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointFoodRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointImpl;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointRequest;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointResponse;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestAddFoodToCheckpoint implements AddFoodToCheckpointPresenter {

  @Autowired InMemoryCheckpointRepository checkpointRepository;

  @Autowired private InMemoryRaceRepository raceRepository;

  @MockBean private FoodPort foodPort;

  private AddFoodToCheckpointImpl addFoodToCheckpoint;

  private AddFoodToCheckpointResponse response;

  @Override
  public void present(AddFoodToCheckpointResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.checkpointRepository.clear();
    this.raceRepository.clear();
    this.addFoodToCheckpoint =
        new AddFoodToCheckpointImpl(this.raceRepository, this.checkpointRepository, this.foodPort);
  }

  @Test
  void should_add_new_food_to_checkpoint() {
    Long userId = 1L;
    Long raceId = 1L;
    Long checkpointId = 1L;
    Long foodId = 1L;
    int quantity = 3;

    Race race =
        new Race("Test Race", LocalDate.parse("2025-01-28"), 10, 100, 100, "Test City", "12345");
    race.setId(raceId);
    race.setUserId(userId);
    this.raceRepository.save(race);

    Checkpoint checkpoint = new Checkpoint("Mid CP", 5, CheckpointType.AID_STATION, 50, 50);
    checkpoint.setId(checkpointId);
    checkpoint.setRace(race);
    race.addOrUpdateCheckpoint(checkpoint);
    this.checkpointRepository.save(checkpoint);

    RaceFoodDto foodDto =
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
            IngestionType.SOLID.name());
    when(foodPort.getFoodById(foodId)).thenReturn(foodDto);
    when(foodPort.getFoodsByIds(any())).thenReturn(Map.of(foodId, foodDto));

    AddFoodToCheckpointRequest request =
        new AddFoodToCheckpointRequest(raceId, checkpointId, foodId, userId, quantity);
    this.addFoodToCheckpoint.execute(request, this);

    Checkpoint updatedCheckpoint = this.response.checkpoint();
    Optional<CheckpointFood> addedFood = updatedCheckpoint.findCheckpointFood(foodId);

    assertThat(addedFood).isPresent();
    assertThat(addedFood.get().getQuantity()).isEqualTo(quantity);
    assertThat(addedFood.get().getFoodId()).isEqualTo(foodId);

    verify(foodPort).getFoodById(foodId);
    verify(foodPort).getFoodsByIds(any());
  }

  @Test
  void should_update_existing_food_quantity() {
    Long userId = 1L;
    Long raceId = 1L;
    Long checkpointId = 1L;
    Long foodId = 1L;
    int initialQuantity = 2;
    int newQuantity = 5;

    Race race =
        new Race("Test Race", LocalDate.parse("2025-01-28"), 10, 100, 100, "Test City", "12345");
    race.setId(raceId);
    race.setUserId(userId);
    this.raceRepository.save(race);

    Checkpoint checkpoint = new Checkpoint("Mid CP", 5, CheckpointType.AID_STATION, 50, 50);
    checkpoint.setId(checkpointId);
    checkpoint.setRace(race);

    CheckpointFood existingFood = new CheckpointFood(checkpoint, initialQuantity, foodId);
    checkpoint.addFood(existingFood);

    race.addOrUpdateCheckpoint(checkpoint);
    this.checkpointRepository.save(checkpoint);

    RaceFoodDto foodDto =
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
            IngestionType.SOLID.name());
    when(foodPort.getFoodById(foodId)).thenReturn(foodDto);
    when(foodPort.getFoodsByIds(any())).thenReturn(Map.of(foodId, foodDto));

    AddFoodToCheckpointRequest request =
        new AddFoodToCheckpointRequest(raceId, checkpointId, foodId, userId, newQuantity);
    this.addFoodToCheckpoint.execute(request, this);

    Checkpoint updatedCheckpoint = this.response.checkpoint();
    Optional<CheckpointFood> updatedFood = updatedCheckpoint.findCheckpointFood(foodId);

    assertThat(updatedFood).isPresent();
    assertThat(updatedFood.get().getQuantity()).isEqualTo(7);

    verify(foodPort).getFoodById(foodId);
    verify(foodPort).getFoodsByIds(any());
  }
}
