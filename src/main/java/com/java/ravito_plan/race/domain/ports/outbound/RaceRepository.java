package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.domain.model.Race;
import java.util.List;

public interface RaceRepository {

    Race save(Race race);

    // TODO
    // getAllByUserId
    // getByUserIdAndId
    // getByUserIdAndName
    //
    List<Race> findAllByUserId(Long userId);
}
