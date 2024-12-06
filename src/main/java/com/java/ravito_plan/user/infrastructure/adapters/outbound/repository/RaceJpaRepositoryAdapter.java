package com.java.ravito_plan.user.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import org.springframework.stereotype.Component;

@Component
public class RaceJpaRepositoryAdapter implements RaceRepository {

    private final ImportedRaceRepository importedRaceRepository;

    public RaceJpaRepositoryAdapter(ImportedRaceRepository importedRaceRepository) {
        this.importedRaceRepository = importedRaceRepository;
    }
}
