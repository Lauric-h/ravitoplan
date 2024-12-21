package com.java.ravito_plan.race.application.dto.command;

import java.time.LocalDate;

public class CreateRaceCommand implements RaceCommand {

    private String name;

    private LocalDate date;

    private int distance;

    private int elevationPositive;

    private int elevationNegative;

    private String city;

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
