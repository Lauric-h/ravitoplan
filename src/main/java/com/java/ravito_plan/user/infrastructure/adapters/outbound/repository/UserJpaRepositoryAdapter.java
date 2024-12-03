package com.java.ravito_plan.user.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.user.application.ports.outbound.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserJpaRepositoryAdapter implements UserRepository {

    private final ImportedUserJpaRepository repository;

    @Autowired
    public UserJpaRepositoryAdapter(ImportedUserJpaRepository importedUserJpaRepository) {
        this.repository = importedUserJpaRepository;
    }
}
