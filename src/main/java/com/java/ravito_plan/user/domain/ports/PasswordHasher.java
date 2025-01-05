package com.java.ravito_plan.user.domain.ports;

public interface PasswordHasher {

    String hash(String rawPassword);
}
