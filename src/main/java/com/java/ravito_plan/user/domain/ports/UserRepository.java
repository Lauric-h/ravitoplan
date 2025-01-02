package com.java.ravito_plan.user.domain.ports;

import com.java.ravito_plan.user.domain.model.User;

public interface UserRepository {
    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);
    boolean existsByUsername(String username);
}
