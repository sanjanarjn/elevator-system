package com.lift.system;

import com.lift.models.Lift;
import com.lift.models.LiftRequest;
import com.lift.models.LiftSystemState;
import lombok.Getter;

@Getter
public class LiftSystem {

    private LiftSystemState systemState;

    public void initialize(LiftSystemConfiguration configuration) {
        systemState = new LiftSystemState();
        int liftCount = configuration.getLiftCount();
        for (int i = 1; i <= liftCount; i++) {
            systemState.addLift(new Lift(i));
        }
    }

    public void handleRequest(LiftRequest request) {

    }
}
