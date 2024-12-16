package com.java.ravito_plan.race.application.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RaceFullDto {
    public String name;

    public LocalDate date;

    public int distance;

    public int elevationPositive;

    public int elevationNegative;

    public String city;

    public String postalCode;

    public List<CheckpointDto> checkpoints = new ArrayList<>();

    public List<SegmentDto> segments = new ArrayList<>();
}
