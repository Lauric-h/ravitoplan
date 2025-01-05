package com.java.ravito_plan.race.domain.usecase.race.showAllRaces;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowAllRacesImpl implements ShowAllRaces {

    private final RaceRepository raceRepository;

    public ShowAllRacesImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowAllRacesRequest request, ShowAllRacesPresenter presenter) {
        List<Race> races = this.raceRepository.findAllByUserId(request.userId());

        presenter.present(new ShowAllRacesResponse(races));
    }
}
