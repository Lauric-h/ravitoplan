package com.java.ravito_plan.race.infrastructure.adapters;

import com.java.ravito_plan.race.domain.dto.CheckpointParams;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.ports.CheckpointFactory;
import org.springframework.stereotype.Component;

@Component
public class CheckpointFactoryImpl implements CheckpointFactory {

    @Override
    public Checkpoint create(CheckpointParams checkpointParams) {
        return new Checkpoint(checkpointParams.name(), checkpointParams.distanceFromStart(),
                checkpointParams.location(), checkpointParams.checkpointType(),
                checkpointParams.cumulatedElevationGainFromStart(),
                checkpointParams.cumulatedElevationLossFromStart(),
                checkpointParams.estimatedTimeInMinuteFromStart(), checkpointParams.carbsTarget());
    }
}
