package com.java.ravito_plan.mock.domain.race;

import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointFoodRepository;
import java.util.HashMap;

public class InMemoryCheckpointFoodRepository implements CheckpointFoodRepository {

  public HashMap<Long, CheckpointFood> checkpointFoods = new HashMap<>();

  public void add(CheckpointFood checkpointFood) {
    this.checkpointFoods.put(checkpointFood.getId(), checkpointFood);
  }

  public void clear() {
    this.checkpointFoods.clear();
  }

  @Override
  public CheckpointFood findByFoodIdAndCheckpointId(Long foodId, Long checkpointId) {
    return this.checkpointFoods.values().stream()
        .filter(
            checkpointFood ->
                checkpointFood.getFoodId().equals(foodId)
                    && checkpointFood.getCheckpoint().getId().equals(checkpointId))
        .findFirst()
        .orElse(null);
  }

  public CheckpointFood save(CheckpointFood checkpointFood) {
    if (checkpointFood.getId() == null) {
      checkpointFood.setId(1L);
    }

    CheckpointFood existing = this.checkpointFoods.putIfAbsent(checkpointFood.getId(), checkpointFood);
    if (existing != null) {
      this.checkpointFoods.replace(checkpointFood.getId(), checkpointFood);
      return existing;
    }
    return this.checkpointFoods.get(checkpointFood.getId());
  }
}
