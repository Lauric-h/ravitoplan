package com.java.ravito_plan.race.domain.usecase.showRace;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowRace implements ShowRaceInterface {

    private final RaceRepository raceRepository;

    public ShowRace(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowRaceRequest request, ShowRacePresenter presenter) {
        Race race = this.raceRepository.findByIdAndUserId(request.raceId(), request.userId());

        presenter.present(new ShowRaceResponse(race));
    }
}
