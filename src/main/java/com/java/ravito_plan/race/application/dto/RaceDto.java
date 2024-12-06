package com.java.ravito_plan.race.application.dto;


public class RaceDto {

    public String name;

    public int distance;

    public int elevationPositive;

    public int elevationNegative;

    public String city;

    public String postalCode;

    public RaceDto(String name, int distance, int elevationPositive, int elevationNegative,
            String city, String postalCode) {
        this.name = name;
        this.distance = distance;
        this.elevationPositive = elevationPositive;
        this.elevationNegative = elevationNegative;
        this.city = city;
        this.postalCode = postalCode;
    }

    // TODO ADD
//    public List<Section> sections;
}
