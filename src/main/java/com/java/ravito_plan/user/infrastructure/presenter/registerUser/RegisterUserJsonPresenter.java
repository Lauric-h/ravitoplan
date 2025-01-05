package com.java.ravito_plan.user.infrastructure.presenter.registerUser;

import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUserPresenter;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUserResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RegisterUserJsonPresenter implements RegisterUserPresenter {

    private RegisterUserViewModel viewModel;

    @Override
    public void present(RegisterUserResponse response) {
        this.viewModel = new RegisterUserViewModel(response.user().getId(),
                response.user().getUsername(), response.user().getEmail());
    }
}
