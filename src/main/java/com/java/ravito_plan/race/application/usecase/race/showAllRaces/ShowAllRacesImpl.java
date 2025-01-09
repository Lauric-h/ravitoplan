package com.java.ravito_plan.race.application.usecase.race.showAllRaces;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRaces;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesPresenter;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesRequest;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesResponse;
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
