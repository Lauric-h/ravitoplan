package com.java.ravito_plan.unit.domain.usecase.race.checkpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.dto.CheckpointParams;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.CheckpointFactory;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointImpl;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointRequest;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointResponse;
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
public class TestUpdateCheckpoint implements UpdateCheckpointPresenter {

  @Autowired private InMemoryCheckpointRepository checkpointRepository;

  @Autowired private InMemoryRaceRepository raceRepository;

  @MockBean
  private FoodPort foodPort;

  @MockBean
  private CheckpointFactory checkpointFactory;

  private UpdateCheckpointImpl updateCheckpoint;

  private UpdateCheckpointResponse response;

  @Override
  public void present(UpdateCheckpointResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.checkpointRepository.clear();
    this.raceRepository.clear();
    this.updateCheckpoint = new UpdateCheckpointImpl(this.checkpointRepository, this.raceRepository, this.foodPort, this.checkpointFactory);
  }

  @Test
  void should_update_checkpoint() {
    Long userId = 1L;
    Long raceId = 1L;
    Long checkpointId = 1L;

    Race race = new Race("Test Race", LocalDate.parse("2025-01-28"), 10, 100, 100, "Test City", "12345");
    race.setId(raceId);
    race.setUserId(userId);
    race.getStartCheckpoint().setId(2L);
    race.getFinishCheckpoint().setId(3L);
    this.raceRepository.save(race);

    Checkpoint initialCheckpoint = new Checkpoint("Initial CP", 5, CheckpointType.NONE, 50, 50);
    initialCheckpoint.setId(checkpointId);
    initialCheckpoint.setRace(race);
    race.addOrUpdateCheckpoint(initialCheckpoint);
    this.checkpointRepository.save(initialCheckpoint);

    CheckpointParams updateParams = new CheckpointParams(
            "Updated CP",
            6,
            "location",
            CheckpointType.AID_STATION,
            null,
            60,
            60,
            200
    );

    Checkpoint updatedCheckpoint = new Checkpoint(
            "Updated CP",
            6,
            CheckpointType.AID_STATION,
            60,
            60
    );
    updatedCheckpoint.setId(checkpointId);
    updatedCheckpoint.setRace(race);

    when(checkpointFactory.create(updateParams)).thenReturn(updatedCheckpoint);
    when(foodPort.getFoodsByIds(any())).thenReturn(new HashMap<>());

    UpdateCheckpointRequest request = new UpdateCheckpointRequest(raceId, checkpointId, userId, updateParams);
    this.updateCheckpoint.execute(request, this);

    Race resultRace = this.response.race();
    Checkpoint resultCheckpoint = resultRace.getCheckpoints().stream()
            .filter(cp -> cp.getId().equals(checkpointId))
            .findFirst()
            .orElseThrow();

    assertThat(resultCheckpoint.getName()).isEqualTo("Updated CP");
    assertThat(resultCheckpoint.getDistanceFromStart()).isEqualTo(6);
    assertThat(resultCheckpoint.getCumulatedElevationGainFromStart()).isEqualTo(60);
    assertThat(resultCheckpoint.getCumulatedElevationLossFromStart()).isEqualTo(60);

    assertThat(resultRace.getCheckpoints())
            .extracting(Checkpoint::getDistanceFromStart)
            .containsExactly(0, 6, 10); // Start, Updated CP, Finish

    verify(checkpointFactory).create(updateParams);
    verify(foodPort).getFoodsByIds(any());
  }
}
