package com.java.ravito_plan.race.infrastructure.adapters;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.user.domain.usecase.getByUsername.GetByUsername;
import com.java.ravito_plan.user.domain.usecase.getByUsername.GetByUsernameRequest;
import com.java.ravito_plan.user.infrastructure.presenter.getByUsername.GetByUsernameRacePresenter;
import org.springframework.stereotype.Component;

@Component
public class UserPortAdapter implements UserPort {


    private final GetByUsername getByUsernameUsecase;
    private final GetByUsernameRacePresenter getByUsernamePresenter;

    public UserPortAdapter(GetByUsername getByUsernameUsecase,
            GetByUsernameRacePresenter getByUsernamePresenter) {
        this.getByUsernameUsecase = getByUsernameUsecase;
        this.getByUsernamePresenter = getByUsernamePresenter;
    }

    @Override
    public RaceUserDto getByUsername(String username) {
        this.getByUsernameUsecase.execute(new GetByUsernameRequest(username),
                this.getByUsernamePresenter);
        return new RaceUserDto(this.getByUsernamePresenter.getViewModel().id(),
                this.getByUsernamePresenter.getViewModel().username());
    }
}
