package com.java.ravito_plan.race.api.checkpoint;

import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpoint;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointRequest;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.updateCheckpoint.UpdateCheckpointJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.updateCheckpoint.UpdateCheckpointViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints/{checkpointId}")
public class UpdateCheckpointController {

    private final UpdateCheckpoint usecase;
    private final UpdateCheckpointJsonPresenter presenter;
    private final UserPort userPort;

    public UpdateCheckpointController(UpdateCheckpoint usecase,
            UpdateCheckpointJsonPresenter presenter, UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @PutMapping
    public ResponseEntity<UpdateCheckpointViewModel> updateCheckpoint(@PathVariable Long raceId, @PathVariable Long checkpointId, @Valid @RequestBody
            UpdateCheckpointCommand command, @AuthenticationPrincipal UserDetails userDetails) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new UpdateCheckpointRequest(raceId, checkpointId, user.id, command), this.presenter);

        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
