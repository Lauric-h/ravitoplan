package com.java.ravito_plan.race.application.dto;

import com.java.ravito_plan.race.domain.model.CheckpointType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckpointDto {

    public String name;
    public int distanceFromStart;
    public String location;
    public String type;
}
