package com.java.ravito_plan.user.domain.service;

import com.java.ravito_plan.user.domain.model.User;
import com.java.ravito_plan.user.domain.ports.outbound.PasswordHasher;
import com.java.ravito_plan.user.domain.ports.outbound.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Transactional
    public User registerUser(String email, String username, String rawPassword) {
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException(String.format("Username %s already exists", username));
        }

        if (this.userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException(String.format("Email %s already exists", email));
        }

        String hashedPassword = this.passwordHasher.hash(rawPassword);

        return new User(username, hashedPassword, email);
    }
}
