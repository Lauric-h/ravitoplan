package com.java.ravito_plan.rest.view.race;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CreateRaceRequest {

    @NotBlank
    public String name;

    @NotBlank
    public LocalDate date;

    @NotBlank
    public int distance;

    @NotBlank
    public int elevationPositive;

    @NotBlank
    public int elevationNegative;

    @NotBlank
    public String city;

    @NotBlank
    public String postalCode;
}
