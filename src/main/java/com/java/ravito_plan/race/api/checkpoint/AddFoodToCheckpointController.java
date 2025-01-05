package com.java.ravito_plan.race.api.checkpoint;

import com.java.ravito_plan.race.application.dto.command.AddFoodCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpoint;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointRequest;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.addFoodToCheckpoint.AddFoodToCheckpointViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints/{checkpointId}/foods")
public class AddFoodToCheckpointController {

    private final AddFoodToCheckpoint usecase;
    private final AddFoodToCheckpointJsonPresenter presenter;
    private final UserPort userPort;

    public AddFoodToCheckpointController(AddFoodToCheckpoint usecase,
            AddFoodToCheckpointJsonPresenter presenter, UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @PostMapping
    public ResponseEntity<AddFoodToCheckpointViewModel> addFoodToCheckpoint(
            @PathVariable Long raceId, @PathVariable Long checkpointId,
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody AddFoodCommand command) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(
                new AddFoodToCheckpointRequest(raceId, checkpointId, user.id, command.getFoodId(),
                        command.getQuantity()), this.presenter);

        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
