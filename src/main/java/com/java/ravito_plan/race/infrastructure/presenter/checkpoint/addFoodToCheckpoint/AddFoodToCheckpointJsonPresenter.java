package com.java.ravito_plan.race.infrastructure.presenter.checkpoint.addFoodToCheckpoint;

import com.java.ravito_plan.race.application.mapper.view.CheckpointViewMapper;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AddFoodToCheckpointJsonPresenter implements AddFoodToCheckpointPresenter {

    private AddFoodToCheckpointViewModel viewModel;

    @Override
    public void present(AddFoodToCheckpointResponse response) {
        this.viewModel = new AddFoodToCheckpointViewModel(CheckpointViewMapper.toCheckpointDetailView(response.checkpoint(), response.foods()));
    }
}
