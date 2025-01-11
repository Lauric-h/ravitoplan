package com.java.ravito_plan.unit.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestCheckpointModel {

  @Test
  public void test_update_checkpoint_details() {
    Checkpoint originalCheckpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    Checkpoint expected =
        new Checkpoint("CP2", 20, "Sommet", CheckpointType.NONE, 2000, 2000, 200, 240);

    Checkpoint actual = originalCheckpoint.updateDetails(expected);

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  public void test_update_checkpoint_details_with_nullable_values() {
    Checkpoint originalCheckpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    Checkpoint expected =
        new Checkpoint("CP2", 20, "Sommet", CheckpointType.NONE, 2000, 2000, null, null);

    Checkpoint actual = originalCheckpoint.updateDetails(expected);

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  public void test_update_checkpointFood_add_quantity() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 2, 1L);

    int quantity = 2;
    int expectedQuantity = 4;

    checkpoint.updateCheckpointFood(checkpointFood, quantity);

    assertThat(checkpointFood.getQuantity()).isEqualTo(expectedQuantity);
  }

  @Test
  public void test_update_checkpointFood_subtract_quantity() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 4, 1L);

    int quantity = -2;
    int expectedQuantity = 2;

    checkpoint.updateCheckpointFood(checkpointFood, quantity);

    assertThat(checkpointFood.getQuantity()).isEqualTo(expectedQuantity);
  }

  @Test
  public void test_update_checkpointFood_new_quantity_is_negative() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 1, 1L);
    checkpoint.addFood(checkpointFood);

    int quantity = -2;

    checkpoint.updateCheckpointFood(checkpointFood, quantity);

    assertThat(checkpoint.getCheckpointFoods().size()).isEqualTo(0);
  }

  @Test
  public void test_update_checkpointFood_new_quantity_is_zero() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 2, 1L);
    checkpoint.addFood(checkpointFood);

    int quantity = -2;

    checkpoint.updateCheckpointFood(checkpointFood, quantity);

    assertThat(checkpoint.getCheckpointFoods().size()).isEqualTo(0);
  }

  @Test
  public void test_remove_checkpointFood() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 2, 1L);
    checkpoint.addFood(checkpointFood);

    checkpoint.removeFood(checkpointFood);

    assertThat(checkpoint.getCheckpointFoods().size()).isEqualTo(0);
  }

  @Test
  public void test_remove_checkpointFood_multiple() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 2, 1L);
    CheckpointFood checkpointFood2 = new CheckpointFood(checkpoint, 1, 1L);
    checkpoint.addFood(checkpointFood);
    checkpoint.addFood(checkpointFood2);

    checkpoint.removeFood(checkpointFood);

    assertThat(checkpoint.getCheckpointFoods().size()).isEqualTo(1);
    assertThat(checkpoint.getCheckpointFoods().get(0).getQuantity()).isEqualTo(1);
  }

  @Test
  public void test_add_food() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 1, 1L);

    checkpoint.addFood(checkpointFood);

    assertThat(checkpoint.getCheckpointFoods().size()).isEqualTo(1);
  }

  @Test
  public void test_add_food_multiple() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 1, 1L);
    CheckpointFood checkpointFood2 = new CheckpointFood(checkpoint, 2, 1L);
    checkpoint.getCheckpointFoods().add(checkpointFood2);

    checkpoint.addFood(checkpointFood);

    assertThat(checkpoint.getCheckpointFoods().size()).isEqualTo(2);
  }

  @Test
  public void test_update_food_quantity_existing_food() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood existingCheckpointFood = new CheckpointFood(checkpoint, 1, 1L);
    checkpoint.addFood(existingCheckpointFood);

    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 1, 1L);
    int quantity = 2;
    checkpoint.updateCheckpointFood(checkpointFood, quantity);
    assertThat(checkpointFood.getQuantity()).isEqualTo(3);
  }

  @Test
  void test_update_food_quantity_new_food() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    CheckpointFood existingCheckpointFood = new CheckpointFood(checkpoint, 1, 1L);
    checkpoint.addFood(existingCheckpointFood);

    CheckpointFood checkpointFood = new CheckpointFood(checkpoint, 1, 2L);
    int quantity = 2;
    checkpoint.updateFoodQuantity(checkpointFood, quantity);

    assertThat(checkpoint.getCheckpointFoods().size()).isEqualTo(2);
  }

  @Test
  public void test_find_checkpointFood() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    Long foodId = 1L;
    CheckpointFood existingCheckpointFood = new CheckpointFood(checkpoint, 1, foodId);
    checkpoint.addFood(existingCheckpointFood);

    Optional<CheckpointFood> actual = checkpoint.findCheckpointFood(foodId);
    assertThat(actual.isPresent()).isTrue();
  }

  @Test
  public void test_find_checkpointFood_is_not_found() {
    Checkpoint checkpoint =
        new Checkpoint("CP1", 10, "Col", CheckpointType.AID_STATION, 1000, 1000, 100, 120);
    Long foodId = 1L;
    CheckpointFood existingCheckpointFood = new CheckpointFood(checkpoint, 1, foodId);
    checkpoint.addFood(existingCheckpointFood);

    Optional<CheckpointFood> actual = checkpoint.findCheckpointFood(2L);
    assertThat(actual.isPresent()).isFalse();
  }
}
