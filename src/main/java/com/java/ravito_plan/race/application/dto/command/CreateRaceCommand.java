package com.java.ravito_plan.race.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateRaceCommand implements RaceCommand {

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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public int getElevationPositive() {
        return this.elevationPositive;
    }

    @Override
    public int getElevationNegative() {
        return this.elevationNegative;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getPostalCode() {
        return this.postalCode;
    }
}
