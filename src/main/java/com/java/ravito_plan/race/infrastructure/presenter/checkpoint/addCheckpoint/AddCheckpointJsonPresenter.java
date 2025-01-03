package com.java.ravito_plan.race.infrastructure.presenter.checkpoint.addCheckpoint;

import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.checkpoint.AddCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.AddCheckpointResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AddCheckpointJsonPresenter implements AddCheckpointPresenter {

    private AddCheckpointViewModel viewModel;

    @Override
    public void present(AddCheckpointResponse response) {
        this.viewModel = new AddCheckpointViewModel(RaceViewMapper.toRaceDetailView(response.race(), response.foods()));
    }
}
