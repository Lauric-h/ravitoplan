package com.java.ravito_plan.race.infrastructure.presenter.checkpoint.deleteCheckpoint;

import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DeleteCheckpointJsonPresenter implements DeleteCheckpointPresenter {

    private DeleteCheckpointViewModel viewModel;

    @Override
    public void present(DeleteCheckpointResponse response) {
        this.viewModel = new DeleteCheckpointViewModel();
    }
}
