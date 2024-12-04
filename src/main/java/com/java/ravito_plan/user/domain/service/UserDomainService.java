package com.java.ravito_plan.user.domain.service;

import com.java.ravito_plan.user.domain.model.User;
import com.java.ravito_plan.user.domain.ports.outbound.PasswordHasher;
import com.java.ravito_plan.user.domain.ports.outbound.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {

    private final PasswordHasher passwordHasher;

    @Autowired
    public UserDomainService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.passwordHasher = passwordHasher;
    }

    public User createUserWithHashedPassword(String email, String username, String rawPassword) {
        String hashedPassword = this.passwordHasher.hash(rawPassword);

        return new User(username, hashedPassword, email);
    }
}
