package com.java.ravito_plan.unit.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.race.domain.exception.CheckpointDistanceOverRaceDistanceException;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.model.Segment;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestRaceModel {

    @Test
    public void test_updateFields() {
      Race race =
          new Race(
              "Before update", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges",
   "74210");

      Race expected =   new Race(
              "After update", LocalDate.parse("2026-01-01"), 20, 2000, 2500, "Annecy", "74000");

      assertThat(race.updateFields(expected)).usingRecursiveComparison().isEqualTo(expected);
    }

  @Test
  public void test_getStartCheckpoint() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    assertThat(race.getStartCheckpoint()).isNotNull();
  }

  @Test
  public void test_getFinishCheckpoint() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    assertThat(race.getFinishCheckpoint()).isNotNull();
  }

  @Test
  public void test_getAllFoodIds() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    Checkpoint start = race.getStartCheckpoint();
    CheckpointFood checkpointFood = new CheckpointFood(start, 1, 1L);
    race.getStartCheckpoint().addFood(checkpointFood);

    Checkpoint finish = race.getFinishCheckpoint();
    CheckpointFood checkpointFood2 = new CheckpointFood(finish, 1, 2L);
    race.getFinishCheckpoint().addFood(checkpointFood2);

    Set<Long> expectedFoodIds = new HashSet<>(Arrays.asList(1L, 2L));
    assertThat(race.getAllFoodIds()).isEqualTo(expectedFoodIds);
  }

  @Test
  public void test_constructor_adds_default_checkpoints() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    assertThat(race.getStartCheckpoint()).isNotNull();
    assertThat(race.getFinishCheckpoint()).isNotNull();
    assertThat(race.getCheckpoints().size()).isEqualTo(2);

    assertThat(race.getStartCheckpoint().getDistanceFromStart()).isEqualTo(0);
    assertThat(race.getStartCheckpoint().getCumulatedElevationGainFromStart()).isEqualTo(0);
    assertThat(race.getStartCheckpoint().getCumulatedElevationLossFromStart()).isEqualTo(0);
    assertThat(race.getStartCheckpoint().getType()).isEqualTo(CheckpointType.START);

    assertThat(race.getFinishCheckpoint().getDistanceFromStart()).isEqualTo(10);
    assertThat(race.getFinishCheckpoint().getCumulatedElevationGainFromStart()).isEqualTo(1000);
    assertThat(race.getFinishCheckpoint().getCumulatedElevationLossFromStart()).isEqualTo(1500);
    assertThat(race.getFinishCheckpoint().getType()).isEqualTo(CheckpointType.FINISH);
  }

  @Test
  public void test_addOrUpdateCheckpoint() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    Checkpoint newCheckpoint = new Checkpoint("Mid", 5, CheckpointType.AID_STATION, 500, 750);
    race.addOrUpdateCheckpoint(newCheckpoint);

    assertThat(race.getCheckpoints()).hasSize(3);
    assertThat(race.getCheckpoints().get(1).getName()).isEqualTo("Mid");
    assertThat(race.getCheckpoints().get(1).getDistanceFromStart()).isEqualTo(5);

    Checkpoint updatedCheckpoint = new Checkpoint("Mid Updated", 5, CheckpointType.NONE, 600, 800);
    race.addOrUpdateCheckpoint(updatedCheckpoint);

    assertThat(race.getCheckpoints()).hasSize(3);
    assertThat(race.getCheckpoints().get(1).getName()).isEqualTo("Mid Updated");
    assertThat(race.getCheckpoints().get(1).getCumulatedElevationGainFromStart()).isEqualTo(600);
  }

  @Test
  public void test_removeCheckpoint() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    Checkpoint midCheckpoint = new Checkpoint("Mid", 5, CheckpointType.NONE, 500, 750);
    race.addOrUpdateCheckpoint(midCheckpoint);

    assertThat(race.getCheckpoints()).hasSize(3);

    race.removeCheckpoint(midCheckpoint);

    assertThat(race.getCheckpoints()).hasSize(2);
    assertThat(race.getCheckpoints()).extracting(Checkpoint::getType)
            .containsExactly(CheckpointType.START, CheckpointType.FINISH);
  }

  @Test
  public void test_getSegments() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    Checkpoint midCheckpoint = new Checkpoint("Mid", 5, CheckpointType.NONE, 500, 750);
    race.addOrUpdateCheckpoint(midCheckpoint);

    List<Segment> segments = race.getSegments();

    assertThat(segments).hasSize(2);

    assertThat(segments.get(0).getStart().getType()).isEqualTo(CheckpointType.START);
    assertThat(segments.get(0).getFinish().getType()).isEqualTo(CheckpointType.NONE);
    assertThat(segments.get(0).getDistance()).isEqualTo(5);

    assertThat(segments.get(1).getStart().getType()).isEqualTo(CheckpointType.NONE);
    assertThat(segments.get(1).getFinish().getType()).isEqualTo(CheckpointType.FINISH);
    assertThat(segments.get(1).getDistance()).isEqualTo(5);
  }

  @Test
  public void test_setDistance_updates_finish_checkpoint() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    race.setDistance(20);

    assertThat(race.getDistance()).isEqualTo(20);
    assertThat(race.getFinishCheckpoint().getDistanceFromStart()).isEqualTo(20);
  }

  @Test
  public void test_setElevationPositive_updates_finish_checkpoint() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    race.setElevationPositive(2000);

    assertThat(race.getElevationPositive()).isEqualTo(2000);
    assertThat(race.getFinishCheckpoint().getCumulatedElevationGainFromStart()).isEqualTo(2000);
  }

  @Test
  public void test_setElevationNegative_updates_finish_checkpoint() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    race.setElevationNegative(2500);

    assertThat(race.getElevationNegative()).isEqualTo(2500);
    assertThat(race.getFinishCheckpoint().getCumulatedElevationLossFromStart()).isEqualTo(2500);
  }

  @Test
  public void test_validate_checkpoint_order() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    Checkpoint checkpoint1 = new Checkpoint("CP1", 3, CheckpointType.NONE, 300, 450);
    Checkpoint checkpoint2 = new Checkpoint("CP2", 7, CheckpointType.NONE, 700, 1050);

    race.addOrUpdateCheckpoint(checkpoint1);
    race.addOrUpdateCheckpoint(checkpoint2);

    assertThat(race.getCheckpoints()).hasSize(4);
    assertThat(race.getCheckpoints()).extracting(Checkpoint::getDistanceFromStart)
            .containsExactly(0, 3, 7, 10);
  }

  @Test
  public void test_checkpoint_distance_validation() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    Checkpoint invalidCheckpoint = new Checkpoint("Invalid", 15, CheckpointType.NONE, 1500, 2000);

    assertThatThrownBy(() -> race.addOrUpdateCheckpoint(invalidCheckpoint))
            .isInstanceOf(CheckpointDistanceOverRaceDistanceException.class);
  }

  @Test
  public void test_addOrUpdateCheckpoint_maintains_order() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    Checkpoint checkpoint3 = new Checkpoint("CP3", 7, CheckpointType.NONE, 700, 800);
    Checkpoint checkpoint2 = new Checkpoint("CP2", 3, CheckpointType.NONE, 300, 400);
    Checkpoint checkpoint1 = new Checkpoint("CP1", 5, CheckpointType.NONE, 500, 600);

    race.addOrUpdateCheckpoint(checkpoint3);  // d=7
    race.addOrUpdateCheckpoint(checkpoint2);  // d=3
    race.addOrUpdateCheckpoint(checkpoint1);  // d=5

    assertThat(race.getCheckpoints())
            .hasSize(5)
            .extracting(Checkpoint::getDistanceFromStart)
            .containsExactly(0, 3, 5, 7, 10);

    assertThat(race.getCheckpoints())
            .extracting(Checkpoint::getName)
            .containsExactly("Start", "CP2", "CP1", "CP3", "Finish");
  }

  @Test
  public void test_addOrUpdateCheckpoint_updates_existing_checkpoint_and_maintains_order() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    Checkpoint checkpoint1 = new Checkpoint("CP1", 3, CheckpointType.NONE, 300, 400);
    Checkpoint checkpoint2 = new Checkpoint("CP2", 7, CheckpointType.NONE, 700, 800);

    race.addOrUpdateCheckpoint(checkpoint1);
    race.addOrUpdateCheckpoint(checkpoint2);

    Checkpoint updatedCheckpoint = new Checkpoint("CP1 Updated", 3, CheckpointType.NONE, 500, 600);
    race.addOrUpdateCheckpoint(updatedCheckpoint);

    assertThat(race.getCheckpoints())
            .hasSize(4)
            .extracting(cp -> String.format("%s-%d", cp.getName(), cp.getDistanceFromStart()))
            .containsExactly(
                    "Start-0",
                    "CP1 Updated-3",
                    "CP2-7",
                    "Finish-10"
            );
  }

  @Test
  public void test_addOrUpdateCheckpoint_maintains_start_and_finish_positions() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");

    Checkpoint checkpoint1 = new Checkpoint("CP1", 5, CheckpointType.NONE, 500, 600);
    race.addOrUpdateCheckpoint(checkpoint1);
    
    assertThat(race.getCheckpoints().get(0).getType()).isEqualTo(CheckpointType.START);
    assertThat(race.getCheckpoints().get(0).getDistanceFromStart()).isEqualTo(0);

    assertThat(race.getCheckpoints().get(race.getCheckpoints().size() - 1).getType())
            .isEqualTo(CheckpointType.FINISH);
    assertThat(race.getCheckpoints().get(race.getCheckpoints().size() - 1).getDistanceFromStart())
            .isEqualTo(race.getDistance());
  }
}
