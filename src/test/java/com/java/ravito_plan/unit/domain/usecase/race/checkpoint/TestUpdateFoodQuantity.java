package com.java.ravito_plan.unit.domain.usecase.race.checkpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointFoodRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantityImpl;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantityPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantityRequest;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantityResponse;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestUpdateFoodQuantity implements UpdateFoodQuantityPresenter {

	@Autowired
	InMemoryCheckpointRepository checkpointRepository;
	@Autowired
	InMemoryCheckpointFoodRepository checkpointFoodRepository;
	@Autowired
	private InMemoryRaceRepository raceRepository;
	@MockBean
	private FoodPort foodPort;

	private UpdateFoodQuantityImpl updateFoodQuantity;

	private UpdateFoodQuantityResponse response;

	@Override
	public void present(UpdateFoodQuantityResponse response) {
		this.response = response;
	}

	@BeforeEach
	public void setUp() {
		this.checkpointRepository.clear();
		this.checkpointFoodRepository.clear();
		this.raceRepository.clear();
		this.updateFoodQuantity = new UpdateFoodQuantityImpl(this.raceRepository, this.checkpointRepository, this.checkpointFoodRepository, this.foodPort);
	}

	@Test
	void should_update_food_quantity() {
		LocalDate raceDate = LocalDate.parse("2025-01-28");
		Long userId = 1L;
		Long raceId = 1L;
		Long checkpointId = 1L;
		Long foodId = 1L;
		int initialQuantity = 2;
		int newQuantity = 5;

		Race race = new Race("Test Race", raceDate, 10, 100, 100, "Test City", "12345");
		race.setId(raceId);
		race.setUserId(userId);
		this.raceRepository.save(race);

    Checkpoint checkpoint = new Checkpoint("Mid CP", 5, CheckpointType.NONE, 50, 50);
		checkpoint.setId(checkpointId);
		checkpoint.setRace(race);
		race.addOrUpdateCheckpoint(checkpoint);

		CheckpointFood checkpointFood = new CheckpointFood(checkpoint, initialQuantity, foodId);
		checkpoint.addFood(checkpointFood);
		this.checkpointRepository.save(checkpoint);
		this.checkpointFoodRepository.save(checkpointFood);

		when(foodPort.getFoodsByIds(any())).thenReturn(new HashMap<>());

		UpdateFoodQuantityRequest request = new UpdateFoodQuantityRequest(raceId, checkpointId, foodId, userId, newQuantity);
		this.updateFoodQuantity.execute(request, this);

		Checkpoint updatedCheckpoint = this.response.checkpoint();
		CheckpointFood updatedCheckpointFood = updatedCheckpoint.getCheckpointFoods().stream()
				.filter(cf -> cf.getFoodId().equals(foodId))
				.findFirst()
				.orElseThrow();

		assertThat(updatedCheckpointFood.getQuantity()).isEqualTo(7);
		verify(foodPort).getFoodsByIds(any());
	}
}
