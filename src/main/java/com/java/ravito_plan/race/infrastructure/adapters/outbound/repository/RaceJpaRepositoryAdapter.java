package com.java.ravito_plan.race.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import org.springframework.stereotype.Component;

@Component
public class RaceJpaRepositoryAdapter implements RaceRepository {

    private final ImportedRaceRepository importedRaceRepository;

    public RaceJpaRepositoryAdapter(ImportedRaceRepository importedRaceRepository) {
        this.importedRaceRepository = importedRaceRepository;
    }

    @Override
    public Race save(Race race) {
        return null;
    }
}
