package com.java.ravito_plan.race.application.dto.view;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RaceDetailView {

    public String name;

    public LocalDate date;

    public int distance;

    public int elevationPositive;

    public int elevationNegative;

    public String city;

    public String postalCode;

    public List<CheckpointView> checkpoints;

    public List<SegmentView> segments;
}
