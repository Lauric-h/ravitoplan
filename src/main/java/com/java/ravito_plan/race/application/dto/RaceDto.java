package com.java.ravito_plan.race.application.dto;


import com.java.ravito_plan.race.domain.model.Race;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RaceDto {

    public String name;

    public LocalDate date;

    public int distance;

    public int elevationPositive;

    public int elevationNegative;

    public String city;

    public String postalCode;

    public RaceDto() {}

    public RaceDto(String name, LocalDate date,int distance, int elevationPositive, int elevationNegative,
            String city, String postalCode) {
        this.name = name;
        this.date = date;
        this.distance = distance;
        this.elevationPositive = elevationPositive;
        this.elevationNegative = elevationNegative;
        this.city = city;
        this.postalCode = postalCode;
    }

    // TODO ADD
//    public List<Section> sections;
}
