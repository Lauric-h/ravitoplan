package com.java.ravito_plan.race.application.usecase.race.createRace;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.RaceFactory;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRace;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRaceResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateRaceImpl implements CreateRace {

    private final RaceRepository raceRepository;
    private final RaceFactory raceFactory;

    public CreateRaceImpl(RaceRepository raceRepository, RaceFactory raceFactory) {
        this.raceRepository = raceRepository;
        this.raceFactory = raceFactory;
    }

    @Override
    @Transactional
    public void execute(CreateRaceRequest request, CreateRacePresenter presenter) {
        Race race = this.raceFactory.create(request.raceParams());
        race.setUserId(request.userId());

        Race createdRace = this.raceRepository.save(race);
        presenter.present(new CreateRaceResponse(createdRace));
    }
}
