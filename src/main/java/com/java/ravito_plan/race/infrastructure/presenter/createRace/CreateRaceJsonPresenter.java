package com.java.ravito_plan.race.infrastructure.presenter.createRace;

import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.createRace.CreateRacePresenter;
import com.java.ravito_plan.race.domain.usecase.createRace.CreateRaceResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CreateRaceJsonPresenter implements CreateRacePresenter {

    private CreateRaceViewModel viewModel;

    @Override
    public void present(CreateRaceResponse response) {
        this.viewModel = new CreateRaceViewModel(RaceViewMapper.toRaceSummaryView(response.race()));
    }
}
