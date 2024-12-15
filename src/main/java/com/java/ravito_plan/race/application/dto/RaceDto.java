package com.java.ravito_plan.race.application.dto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RaceDto {

    public String name;

    public LocalDate date;

    public int distance;

    public int elevationPositive;

    public int elevationNegative;

    public String city;

    public String postalCode;

    public List<CheckpointDto> checkpoints = new ArrayList<>();

    public List<SegmentDto> segments = new ArrayList<>();

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
}
