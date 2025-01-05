package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRace;
import com.java.ravito_plan.race.infrastructure.presenter.race.updateRace.UpdateRaceJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.race.updateRace.UpdateRaceViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{id}")
public class UpdateRaceController {

    private final UpdateRaceJsonPresenter presenter;
    private final UpdateRace usecase;

    public UpdateRaceController(UpdateRaceJsonPresenter presenter, UpdateRace usecase) {
        this.presenter = presenter;
        this.usecase = usecase;
    }

    @PutMapping
    public ResponseEntity<UpdateRaceViewModel> updateRace(@PathVariable Long id,
            @CurrentUser RaceUserDto user, @Valid @RequestBody UpdateRaceCommand command) {
        this.usecase.execute(RaceMapper.toUpdateRaceRequest(user.id, id, command), presenter);

        return ResponseEntity.ok(this.presenter.getViewModel());

    }
}
