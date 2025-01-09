package com.java.ravito_plan.race.application.usecase.race.updateRace;

import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.RaceFactory;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRace;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRaceResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateRaceImpl implements UpdateRace {

    private final RaceRepository raceRepository;
    private final RaceFactory raceFactory;

    public UpdateRaceImpl(RaceRepository raceRepository, RaceFactory raceFactory) {
        this.raceRepository = raceRepository;
        this.raceFactory = raceFactory;
    }

    @Override
    @Transactional
    public void execute(UpdateRaceRequest request, UpdateRacePresenter presenter) {
        Race race = this.raceRepository.findByIdAndUserId(request.raceId(), request.userId());

        race.updateFields(this.raceFactory.create(request.raceParams()));
        race.validate();

        Race updatedRace = this.raceRepository.save(race);

        presenter.present(new UpdateRaceResponse(updatedRace));
    }
}
