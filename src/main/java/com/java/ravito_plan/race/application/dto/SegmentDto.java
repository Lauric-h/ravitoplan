package com.java.ravito_plan.race.application.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SegmentDto {
    public int start;
    public int finish;
    public int distance;
    public Integer estimatedTime;
    public int elevationGain;
    public int elevationLoss;
}
