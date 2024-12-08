package com.java.ravito_plan.user.application.dto;


public class UserDto {

    public Long id;
    public String username;
    public String email;

    public UserDto() {
    }

    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
