package com.java.ravito_plan.race.application.dto;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RaceDto {

    public String name;

    public LocalDate date;

    public int distance;

    public int elevationPositive;

    public int elevationNegative;

    public String city;

    public String postalCode;
}
