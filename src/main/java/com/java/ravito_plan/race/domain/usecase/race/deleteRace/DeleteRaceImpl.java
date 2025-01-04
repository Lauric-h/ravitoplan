package com.java.ravito_plan.race.domain.usecase.race.deleteRace;

import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteRaceImpl implements DeleteRace {

    private final RaceRepository raceRepository;

    public DeleteRaceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional
    public void execute(DeleteRaceRequest request, DeleteRacePresenter presenter) {
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());
        this.raceRepository.deleteById(request.raceId());
        presenter.present(new DeleteRaceResponse(true));
    }
}
