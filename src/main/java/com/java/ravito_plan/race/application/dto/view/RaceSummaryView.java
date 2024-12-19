package com.java.ravito_plan.race.application.dto.view;

import java.time.LocalDate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RaceSummaryView {

    public String name;

    public LocalDate date;

    public int distance;

    public int elevationPositive;

    public int elevationNegative;

    public String city;

    public String postalCode;
}
