package com.java.ravito_plan.user.application.service;

import com.java.ravito_plan.user.application.dto.UserDto;
import com.java.ravito_plan.user.application.dto.auth.RegisterUserCommand;
import com.java.ravito_plan.user.domain.exception.UserAlreadyExistsException;
import com.java.ravito_plan.user.domain.model.User;
import com.java.ravito_plan.user.domain.ports.outbound.UserRepository;
import com.java.ravito_plan.user.domain.service.UserDomainService;
import com.java.ravito_plan.user.domain.ports.inbound.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationService implements UserPort {

    private final UserRepository userRepository;
    private final UserDomainService userService;

    @Autowired
    public UserApplicationService(UserRepository userRepository, UserDomainService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDto registerUser(RegisterUserCommand registerUserCommand) {
        if (this.userRepository.findByUsername(registerUserCommand.username) != null) {
            throw new UserAlreadyExistsException(registerUserCommand.username);
        }

        if (this.userRepository.findByEmail(registerUserCommand.email) != null) {
            throw new UserAlreadyExistsException(registerUserCommand.email);
        }

        User user = this.userService.createUserWithHashedPassword(registerUserCommand.email,
                registerUserCommand.username, registerUserCommand.password);

        User savedUser = this.userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    public UserDto getByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }
}
