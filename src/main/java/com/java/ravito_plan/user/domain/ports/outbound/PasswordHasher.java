package com.java.ravito_plan.user.domain.ports.outbound;

public interface PasswordHasher {

    String hash(String rawPassword);
}
