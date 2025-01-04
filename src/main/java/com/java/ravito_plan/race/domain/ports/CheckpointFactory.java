package com.java.ravito_plan.race.domain.ports;

import com.java.ravito_plan.race.domain.dto.CheckpointParams;
import com.java.ravito_plan.race.domain.model.Checkpoint;

public interface CheckpointFactory {

    Checkpoint create(CheckpointParams checkpointParams);
}
