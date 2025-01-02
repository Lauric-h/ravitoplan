package com.java.ravito_plan.race.domain.usecase.updateRace;

import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateRace implements UpdateRaceInterface {

    private final RaceRepository raceRepository;

    public UpdateRace(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional
    public void execute(UpdateRaceRequest request, UpdateRacePresenter presenter) {
        Race race = this.raceRepository.findByIdAndUserId(request.command().getId(), request.userId());

        race.updateFields(RaceMapper.toRace(request.command()));
        race.validate();

        Race updatedRace = this.raceRepository.save(race);

        presenter.present(new UpdateRaceResponse(updatedRace));
    }
}
