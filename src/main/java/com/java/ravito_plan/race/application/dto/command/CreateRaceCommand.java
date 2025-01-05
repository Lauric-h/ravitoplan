package com.java.ravito_plan.race.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class CreateRaceCommand{

    @NotBlank
    private String name;

    @NotNull
    private LocalDate date;

    @NotNull
    private int distance;

    @NotNull
    private int elevationPositive;

    @NotNull
    private int elevationNegative;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;
}
