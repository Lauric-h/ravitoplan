package com.java.ravito_plan.race.domain.usecase.showAllRaces;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShowAllRaces implements ShowAllRacesInterface {

    private final RaceRepository raceRepository;

    public ShowAllRaces(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowAllRacesRequest request, ShowAllRacesPresenter presenter) {
        List<Race> races = this.raceRepository.findAllByUserId(request.userId());

        presenter.present(new ShowAllRacesResponse(races));
    }
}
