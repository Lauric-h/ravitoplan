package com.java.ravito_plan.race.infrastructure.presenter.checkpoint.updateFoodQuantityJsonPresenter;

import com.java.ravito_plan.race.application.mapper.view.CheckpointViewMapper;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantityPresenter;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantityResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UpdateFoodQuantityJsonPresenter implements UpdateFoodQuantityPresenter {

    private UpdateFoodQuantityViewModel viewModel;

    @Override
    public void present(UpdateFoodQuantityResponse response) {
        this.viewModel = new UpdateFoodQuantityViewModel(
                CheckpointViewMapper.toCheckpointDetailView(response.checkpoint(),
                        response.foods()));
    }
}
