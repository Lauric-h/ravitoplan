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
import com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint.AddCheckpoint;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint.AddCheckpointImpl;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint.AddCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint.AddCheckpointRequest;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint.AddCheckpointResponse;
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
public class TestAddCheckpoint implements AddCheckpointPresenter {

  @Autowired private InMemoryRaceRepository repository;

  @MockBean private FoodPort foodPort;

  @MockBean private CheckpointFactory checkpointFactory;

  private AddCheckpointResponse response;

  private AddCheckpointImpl addCheckpoint;

  @Override
  public void present(AddCheckpointResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.repository.clear();
    this.addCheckpoint =
        new AddCheckpointImpl(this.repository, this.foodPort, this.checkpointFactory);
  }

  @Test
  void should_add_checkpoint_to_race() {
    Long userId = 1L;
    Race race =
        new Race("Test Race", LocalDate.parse("2025-01-28"), 10, 100, 100, "Test City", "12345");
    race.setId(1L);
    race.setUserId(userId);
    this.repository.save(race);

    CheckpointParams checkpointParams =
        new CheckpointParams(
            "New Checkpoint",
            5,
            "location",
            CheckpointType.AID_STATION,
            null,
            50,
            50,
            120);

    Checkpoint expectedCheckpoint =
        new Checkpoint("New Checkpoint", 5, CheckpointType.AID_STATION, 50, 50);

    when(checkpointFactory.create(checkpointParams)).thenReturn(expectedCheckpoint);
    when(foodPort.getFoodsByIds(any())).thenReturn(new HashMap<>());

    AddCheckpointRequest request = new AddCheckpointRequest(1L, userId, checkpointParams);
    this.addCheckpoint.execute(request, this);

    Race updatedRace = this.response.race();
    assertThat(updatedRace.getCheckpoints())
        .hasSize(3)
        .extracting(Checkpoint::getName)
        .containsExactly("Start", "New Checkpoint", "Finish");

    Checkpoint addedCheckpoint =
        updatedRace.getCheckpoints().get(1);
    assertThat(addedCheckpoint.getName()).isEqualTo("New Checkpoint");
    assertThat(addedCheckpoint.getDistanceFromStart()).isEqualTo(5);
    assertThat(addedCheckpoint.getType()).isEqualTo(CheckpointType.AID_STATION);
    assertThat(addedCheckpoint.getCumulatedElevationGainFromStart()).isEqualTo(50);
    assertThat(addedCheckpoint.getCumulatedElevationLossFromStart()).isEqualTo(50);

    verify(checkpointFactory).create(checkpointParams);
    verify(foodPort).getFoodsByIds(any());
  }
}
