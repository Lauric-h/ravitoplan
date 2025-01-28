package com.java.ravito_plan.mock.domain.race;

import com.java.ravito_plan.race.application.exception.CheckpointNotFoundException;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import java.util.HashMap;

public class InMemoryCheckpointRepository implements CheckpointRepository {

	public HashMap<Long, Checkpoint> checkpoints = new HashMap<>();

	public void addCheckpoint(Checkpoint checkpoint) {
		this.checkpoints.put(checkpoint.getId(), checkpoint);
	}

	public void clear() {
		checkpoints.clear();
	}

	@Override
	public Checkpoint findById(Long id) {
		return checkpoints.get(id);
	}

	@Override
	public Checkpoint save(Checkpoint checkpoint) {
		if (checkpoint.getId() == null) {
			checkpoint.setId(1L);
		}
		Checkpoint existingCheckpoint = this.checkpoints.putIfAbsent(checkpoint.getId(), checkpoint);
		if (existingCheckpoint != null) {
			this.checkpoints.replace(checkpoint.getId(), checkpoint);
			return existingCheckpoint;
		}
		return this.checkpoints.get(checkpoint.getId());
	}

	@Override
	public void deleteById(Long id) {
		this.checkpoints.remove(id);
	}

	@Override
	public Checkpoint findByIdAndRaceId(Long id, Long raceId) {
		Checkpoint checkpoint = this.checkpoints.get(id);
		if (!checkpoint.getRace().getId().equals(raceId)) {
			throw new CheckpointNotFoundException(id);
		}
		return checkpoint;
	}
}
