package com.java.ravito_plan.user.domain.usecase.registerUser;

import com.java.ravito_plan.common.exception.NotFoundException;
import com.java.ravito_plan.user.domain.exception.UserAlreadyExistsException;
import com.java.ravito_plan.user.domain.model.User;
import com.java.ravito_plan.user.domain.ports.UserRepository;
import com.java.ravito_plan.user.domain.service.UserDomainService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegisterUserImpl implements RegisterUser {

  private final UserRepository userRepository;
  private final UserDomainService userService;

  public RegisterUserImpl(UserRepository userRepository, UserDomainService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @Override
  @Transactional
  public void execute(RegisterUserRequest request, RegisterUserPresenter presenter) {
    boolean userAlreadyExists =
        this.userRepository.existsByUsernameOrEmail(request.username(), request.email());

    if (userAlreadyExists) {
      throw new UserAlreadyExistsException(request.username(), request.email());
    }

    User user =
        this.userService.createUserWithHashedPassword(
            request.email(), request.username(), request.password());

    User savedUser = this.userRepository.save(user);

    presenter.present(new RegisterUserResponse(savedUser));
  }
}
