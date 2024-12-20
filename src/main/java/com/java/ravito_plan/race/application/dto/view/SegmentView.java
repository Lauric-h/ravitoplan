package com.java.ravito_plan.race.application.dto.view;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SegmentView {

    public int start;
    public int finish;
    public int distance;
    public Integer estimatedTime;
    public int elevationGain;
    public int elevationLoss;
}
