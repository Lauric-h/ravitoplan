package com.java.ravito_plan.race.api.checkpoint;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.command.UpdateFoodQuantityCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantity;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity.UpdateFoodQuantityRequest;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.updateFoodQuantityJsonPresenter.UpdateFoodQuantityJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.updateFoodQuantityJsonPresenter.UpdateFoodQuantityViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints/{checkpointId}/foods/{foodId}")
public class UpdateFoodQuantityController {

    private final UpdateFoodQuantity usecase;
    private final UpdateFoodQuantityJsonPresenter presenter;

    public UpdateFoodQuantityController(UpdateFoodQuantity usecase,
            UpdateFoodQuantityJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @PatchMapping
    public ResponseEntity<UpdateFoodQuantityViewModel> updateFoodQuantity(@PathVariable Long raceId,
            @PathVariable Long checkpointId, @PathVariable Long foodId,
            @CurrentUser RaceUserDto user, @RequestBody UpdateFoodQuantityCommand command) {
        this.usecase.execute(new UpdateFoodQuantityRequest(raceId, user.id, checkpointId, foodId,
                command.quantity()), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
