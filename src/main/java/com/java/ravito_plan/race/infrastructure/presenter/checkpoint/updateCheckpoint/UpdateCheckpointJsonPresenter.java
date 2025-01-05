package com.java.ravito_plan.race.infrastructure.presenter.checkpoint.updateCheckpoint;

import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UpdateCheckpointJsonPresenter implements UpdateCheckpointPresenter {

    private UpdateCheckpointViewModel viewModel;

    @Override
    public void present(UpdateCheckpointResponse response) {
        this.viewModel = new UpdateCheckpointViewModel(RaceViewMapper.toRaceDetailView(response.race(), response.foods()));
    }
}
