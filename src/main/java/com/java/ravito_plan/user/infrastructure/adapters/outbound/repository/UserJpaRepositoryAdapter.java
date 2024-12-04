package com.java.ravito_plan.user.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.user.domain.ports.outbound.repository.UserRepository;
import com.java.ravito_plan.user.domain.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserJpaRepositoryAdapter implements UserRepository {

    private final ImportedUserJpaRepository repository;

    @Autowired
    public UserJpaRepositoryAdapter(ImportedUserJpaRepository importedUserJpaRepository) {
        this.repository = importedUserJpaRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }
}
