package com.java.ravito_plan.race.infrastructure.presenter.checkpoint.removeFoodFromCheckpoint;

import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RemoveFoodFromCheckpointJsonPresenter implements RemoveFoodFromCheckpointPresenter {

    private RemoveFoodFromCheckpointViewModel viewModel;

    @Override
    public void present(RemoveFoodFromCheckpointResponse response) {
        this.viewModel = new RemoveFoodFromCheckpointViewModel();
    }
}
