package com.java.ravito_plan.race.domain.usecase.showAllRaces;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ShowAllRaces implements ShowAllRacesInterface {

    private final RaceRepository raceRepository;

    public ShowAllRaces(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public void execute(ShowAllRacesRequest request, ShowAllRacesPresenter presenter) {
        List<Race> races = this.raceRepository.findAllByUserId(request.userId());

        ShowAllRacesResponse response = new ShowAllRacesResponse(races);

        presenter.present(response);
    }
}
