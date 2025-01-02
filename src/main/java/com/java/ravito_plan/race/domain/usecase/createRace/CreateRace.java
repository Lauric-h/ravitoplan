package com.java.ravito_plan.race.domain.usecase.createRace;

import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateRace implements CreateRaceInterface {

    private final RaceRepository raceRepository;

    public CreateRace(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional
    public void execute(CreateRaceRequest request, CreateRacePresenter presenter) {
        Race race = RaceMapper.toRace(request.command());
        race.setUserId(request.userId());

        Race createdRace = this.raceRepository.save(race);
        presenter.present(new CreateRaceResponse(createdRace));
    }
}
