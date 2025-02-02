package com.java.ravito_plan.race.infrastructure.repository;

import com.java.ravito_plan.race.application.exception.RaceNotFoundException;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RaceJpaRepositoryAdapter implements RaceRepository {

    private final ImportedRaceRepository importedRaceRepository;

    public RaceJpaRepositoryAdapter(ImportedRaceRepository importedRaceRepository) {
        this.importedRaceRepository = importedRaceRepository;
    }

    @Override
    public Race save(Race race) {
        return this.importedRaceRepository.save(race);
    }

    @Override
    public List<Race> findAllByUserId(Long userId) {
        return this.importedRaceRepository.findAllByUserId(userId);
    }

    @Override
    public Race findByIdAndUserId(Long id, Long userId) {
        return this.importedRaceRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new RaceNotFoundException(id));
    }

    @Override
    public boolean existsByIdAndUserId(Long id, Long userId) {
        return this.importedRaceRepository.existsByIdAndUserId(id, userId);
    }

    @Override
    public void deleteById(Long id) {
        this.importedRaceRepository.deleteById(id);
    }
}
