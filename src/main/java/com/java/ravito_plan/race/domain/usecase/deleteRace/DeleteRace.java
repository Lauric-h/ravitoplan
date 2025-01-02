package com.java.ravito_plan.race.domain.usecase.deleteRace;

import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteRace implements DeleteRaceInterface {

    private final RaceRepository raceRepository;

    public DeleteRace(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public void execute(DeleteRaceRequest request, DeleteRacePresenter presenter) {
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());
        this.raceRepository.deleteById(request.raceId());
    }
}
