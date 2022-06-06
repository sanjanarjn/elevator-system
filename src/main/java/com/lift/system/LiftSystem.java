package com.lift.system;

import com.lift.models.Lift;
import com.lift.models.PickupRequest;
import com.lift.scheduling.LiftSystemState;
import com.lift.scheduling.LiftScheduler;
import lombok.Getter;

@Getter
public class LiftSystem {

    private LiftSystemState systemState;
    private LiftScheduler scheduler;

    public void initialize(LiftSystemConfiguration configuration) {
        systemState = new LiftSystemState();
        int liftCount = configuration.getLiftCount();
        for (int i = 1; i <= liftCount; i++) {
            systemState.addLift(new Lift(i));
        }
    }

    public void acceptPickupRequest(PickupRequest request) {
        scheduler.handlePickupRequest(request, systemState);
    }
}
