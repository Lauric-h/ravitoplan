package com.java.ravito_plan.race.domain.usecase.race.showRace;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowRaceImpl implements ShowRace {

    private final RaceRepository raceRepository;

    public ShowRaceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowRaceRequest request, ShowRacePresenter presenter) {
        Race race = this.raceRepository.findByIdAndUserId(request.raceId(), request.userId());

        presenter.present(new ShowRaceResponse(race));
    }
}
