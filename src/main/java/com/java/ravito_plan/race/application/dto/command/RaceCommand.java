package com.java.ravito_plan.race.application.dto.command;

import java.time.LocalDate;

public interface RaceCommand {

    String getName();
    LocalDate getDate();
    int getDistance();
    int getElevationPositive();
    int getElevationNegative();
    String getCity();
    String getPostalCode();
}
