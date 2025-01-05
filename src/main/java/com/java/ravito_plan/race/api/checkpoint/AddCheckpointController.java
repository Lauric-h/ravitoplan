package com.java.ravito_plan.race.api.checkpoint;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.mapper.CheckpointMapper;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint.AddCheckpoint;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.addCheckpoint.AddCheckpointJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.addCheckpoint.AddCheckpointViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints")
public class AddCheckpointController {

    private final AddCheckpoint usecase;
    private final AddCheckpointJsonPresenter presenter;

    public AddCheckpointController(AddCheckpoint usecase, AddCheckpointJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }


    @PostMapping
    public ResponseEntity<AddCheckpointViewModel> addCheckpoint(@PathVariable Long raceId,
            @Valid @RequestBody CreateCheckpointCommand command, @CurrentUser RaceUserDto user) {
        this.usecase.execute(CheckpointMapper.toAddCheckpointRequest(user.id, raceId, command),
                this.presenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.presenter.getViewModel());
    }
}
