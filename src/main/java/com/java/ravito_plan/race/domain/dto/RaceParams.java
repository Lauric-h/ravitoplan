package com.java.ravito_plan.race.domain.dto;

import java.time.LocalDate;

public record RaceParams(String name, LocalDate date, int distance, int elevationPositive,
                         int elevationNegative, String city, String postalCode) {

}
