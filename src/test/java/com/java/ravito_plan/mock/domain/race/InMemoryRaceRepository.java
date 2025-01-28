package com.java.ravito_plan.mock.domain.race;

import com.java.ravito_plan.race.application.exception.RaceNotFoundException;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryRaceRepository implements RaceRepository {

  public final HashMap<Long, Race> races = new HashMap<>();

  public void addRace(Race race) {
    this.races.put(race.getId(), race);
  }

  public void clear() {
    this.races.clear();
  }

  @Override
  public Race save(Race race) {
    if (race.getId() == null) {
      race.setId(1L);
    }

    Race existingRace = this.races.putIfAbsent(race.getId(), race);
    if (existingRace != null) {
      this.races.replace(race.getId(), race);
      return existingRace;
    }
    return this.races.get(race.getId());
  }

  @Override
  public List<Race> findAllByUserId(Long userId) {
    return this.races.values().stream()
        .filter(r -> r.getUserId().equals(userId))
        .collect(Collectors.toList());
  }

  @Override
  public Race findByIdAndUserId(Long id, Long userId) {
    Race race = this.races.get(id);
    if (race == null || !race.getUserId().equals(userId)) {
      throw new RaceNotFoundException(id);
    }
    return race;
  }

  @Override
  public boolean existsByIdAndUserId(Long id, Long userId) {
    return this.races.containsKey(id) && this.races.get(id).getUserId().equals(userId);
  }

  @Override
  public void deleteById(Long id) {
    this.races.remove(id);
  }
}
