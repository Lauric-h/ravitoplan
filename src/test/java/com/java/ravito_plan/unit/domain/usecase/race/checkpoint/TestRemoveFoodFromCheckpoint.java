package com.java.ravito_plan.unit.domain.usecase.race.checkpoint;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointFoodRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointImpl;
import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointRequest;
import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointResponse;
import java.time.LocalDate;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestRemoveFoodFromCheckpoint implements RemoveFoodFromCheckpointPresenter {

	@Autowired
	InMemoryCheckpointRepository checkpointRepository;

	@Autowired
	private InMemoryRaceRepository raceRepository;

	private RemoveFoodFromCheckpointImpl removeFoodFromCheckpoint;

	private RemoveFoodFromCheckpointResponse response;

	@Override
	public void present(RemoveFoodFromCheckpointResponse response) {
		this.response = response;
	}

	@BeforeEach
	public void setUp() {
		this.checkpointRepository.clear();
		this.raceRepository.clear();
		this.removeFoodFromCheckpoint = new RemoveFoodFromCheckpointImpl(checkpointRepository, raceRepository);
	}

	@Test
	void should_remove_food_from_checkpoint() {
		Long userId = 1L;
		Long raceId = 1L;
		Long checkpointId = 1L;
		Long foodId = 1L;

		Race race = new Race("Test Race", LocalDate.parse("2025-01-28"), 10, 100, 100, "Test City", "12345");
		race.setId(raceId);
		race.setUserId(userId);
		this.raceRepository.save(race);

    Checkpoint checkpoint = new Checkpoint("Mid CP", 5, CheckpointType.AID_STATION, 50, 50);
		checkpoint.setId(checkpointId);
		checkpoint.setRace(race);

		CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 2, foodId);
		checkpoint.addFood(checkpointFood);

		race.addOrUpdateCheckpoint(checkpoint);
		this.checkpointRepository.save(checkpoint);

		assertThat(checkpoint.getCheckpointFoods()).hasSize(1);

		RemoveFoodFromCheckpointRequest request = new RemoveFoodFromCheckpointRequest(raceId, checkpointId, userId, foodId, 2);
		this.removeFoodFromCheckpoint.execute(request, this);

		assertThat(this.response.success()).isTrue();

		Checkpoint updatedCheckpoint = this.checkpointRepository.findByIdAndRaceId(checkpointId, raceId);
		assertThat(updatedCheckpoint.getCheckpointFoods()).isEmpty();
		assertThat(updatedCheckpoint.findCheckpointFood(foodId)).isEmpty();
	}


}
