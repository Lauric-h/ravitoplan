package com.java.ravito_plan.unit.domain.usecase.race.checkpoint;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpoint;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointImpl;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointRequest;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestDeleteCheckpoint implements DeleteCheckpointPresenter {

  @Autowired private InMemoryRaceRepository raceRepository;

  @Autowired private InMemoryCheckpointRepository checkpointRepository;

  private DeleteCheckpoint deleteCheckpoint;

  private DeleteCheckpointResponse response;

  @Override
  public void present(DeleteCheckpointResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.raceRepository.clear();
    this.deleteCheckpoint =
        new DeleteCheckpointImpl(this.checkpointRepository, this.raceRepository);
  }

  @Test
  public void should_delete_checkpoint_from_race() {
    LocalDate raceDate = LocalDate.parse("2025-01-28");
    Long userId = 1L;

    Race race = new Race("Test Race", raceDate, 10, 100, 100, "Test City", "12345");
    race.setId(1L);
    race.setUserId(userId);
    this.raceRepository.save(race);

    Checkpoint checkpoint = new Checkpoint("Mid CP", 5, CheckpointType.AID_STATION, 50, 50);
    checkpoint.setId(1L);
    checkpoint.setRace(race);
    race.addOrUpdateCheckpoint(checkpoint);
    this.checkpointRepository.addCheckpoint(checkpoint);

    assertThat(this.checkpointRepository.findByIdAndRaceId(1L, 1L)).isNotNull();

    DeleteCheckpointRequest request = new DeleteCheckpointRequest(1L, 1L, userId);
    this.deleteCheckpoint.execute(request, this);

    assertThat(this.response.success()).isTrue();
    assertThat(this.checkpointRepository.checkpoints).isEmpty();
  }
}
