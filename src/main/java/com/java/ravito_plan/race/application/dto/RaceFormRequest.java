package com.java.ravito_plan.race.application.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RaceFormRequest {

    private String name;

    private String date;

    private int distance;

    private int elevationPositive;

    private int elevationNegative;

    private String city;

    private String postalCode;
}
