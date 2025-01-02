package com.java.ravito_plan.race.infrastructure.presenter.updateRace;

import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.updateRace.UpdateRacePresenter;
import com.java.ravito_plan.race.domain.usecase.updateRace.UpdateRaceResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UpdateRaceJsonPresenter implements UpdateRacePresenter {

    private UpdateRaceViewModel viewModel;

    @Override
    public void present(UpdateRaceResponse response) {
        this.viewModel = new UpdateRaceViewModel(RaceViewMapper.toRaceSummaryView(response.race()));
    }
}
