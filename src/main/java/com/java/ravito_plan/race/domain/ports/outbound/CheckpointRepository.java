package com.java.ravito_plan.race.domain.ports.outbound;


import com.java.ravito_plan.race.domain.model.Checkpoint;

public interface CheckpointRepository {

    Checkpoint findById(Long id);
    Checkpoint save(Checkpoint checkpoint);
    void deleteById(Long id);
}
