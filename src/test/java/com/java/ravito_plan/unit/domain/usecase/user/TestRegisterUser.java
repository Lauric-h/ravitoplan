package com.java.ravito_plan.unit.domain.usecase.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.user.domain.exception.UserAlreadyExistsException;
import com.java.ravito_plan.user.domain.model.User;
import com.java.ravito_plan.user.domain.ports.UserRepository;
import com.java.ravito_plan.user.domain.service.UserDomainService;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUser;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUserImpl;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUserPresenter;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUserRequest;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestRegisterUser implements RegisterUserPresenter {

  private RegisterUserResponse response;

  @MockBean private UserRepository userRepository;

  @MockBean private UserDomainService userService;
  private RegisterUser registerUser;

  @BeforeEach
  void setUp() {
    this.response = null;
    this.registerUser = new RegisterUserImpl(this.userRepository, this.userService);
  }

  @Override
  public void present(RegisterUserResponse response) {
    this.response = response;
  }

  @Test
  void should_register_new_user() {
    String email = "test@example.com";
    String username = "testuser";
    String password = "password123";

    User expectedUser = new User();
    expectedUser.setId(1L);
    expectedUser.setEmail(email);
    expectedUser.setUsername(username);
    expectedUser.setPassword("hashedPassword");

    when(this.userRepository.existsByUsernameOrEmail(username, email)).thenReturn(false);
    when(this.userService.createUserWithHashedPassword(email, username, password))
        .thenReturn(expectedUser);
    when(this.userRepository.save(expectedUser)).thenReturn(expectedUser);

    RegisterUserRequest request = new RegisterUserRequest(username, password, email);
    this.registerUser.execute(request, this);

    assertThat(this.response).isNotNull();
    assertThat(this.response.user()).usingRecursiveComparison().isEqualTo(expectedUser);
  }

  @Test
  void should_throw_exception_when_username_already_exists() {
    String email = "test@example.com";
    String username = "existinguser";
    String password = "password123";

    when(userRepository.existsByUsernameOrEmail(username, email)).thenReturn(true);

    RegisterUserRequest request = new RegisterUserRequest(username, password, email);

    assertThatThrownBy(() -> this.registerUser.execute(request, this))
        .isInstanceOf(UserAlreadyExistsException.class)
        .hasMessageContaining(username)
        .hasMessageContaining(email);

    verify(userRepository).existsByUsernameOrEmail(username, email);
    verify(userService, never()).createUserWithHashedPassword(any(), any(), any());
    verify(userRepository, never()).save(any());
  }

  @Test
  void should_throw_exception_when_email_already_exists() {
    String email = "existing@example.com";
    String username = "newuser";
    String password = "password123";

    when(userRepository.existsByUsernameOrEmail(username, email)).thenReturn(true);

    RegisterUserRequest request = new RegisterUserRequest(username, password, email);

    assertThatThrownBy(() -> this.registerUser.execute(request, this))
        .isInstanceOf(UserAlreadyExistsException.class)
        .hasMessageContaining(username)
        .hasMessageContaining(email);

    verify(userRepository).existsByUsernameOrEmail(username, email);
    verify(userService, never()).createUserWithHashedPassword(any(), any(), any());
    verify(userRepository, never()).save(any());
  }
}
