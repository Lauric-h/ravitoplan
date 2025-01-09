package com.java.ravito_plan.race.application.usecase.race.deleteRace;

import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRace;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRaceResponse;
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
