package com.java.ravito_plan.user.domain.usecase.registerUser;

public interface RegisterUser {

    void execute(RegisterUserRequest request, RegisterUserPresenter presenter);
}
