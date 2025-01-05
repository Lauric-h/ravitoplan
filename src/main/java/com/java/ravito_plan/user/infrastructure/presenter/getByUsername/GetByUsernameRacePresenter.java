package com.java.ravito_plan.user.infrastructure.presenter.getByUsername;

import com.java.ravito_plan.user.domain.usecase.getByUsername.GetByUsernamePresenter;
import com.java.ravito_plan.user.domain.usecase.getByUsername.GetByUsernameResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GetByUsernameRacePresenter implements GetByUsernamePresenter {

    private GetByUsernameViewModel viewModel;

    @Override
    public void present(GetByUsernameResponse response) {
        this.viewModel = new GetByUsernameViewModel(response.user().getId(),
                response.user().getUsername());
    }
}
