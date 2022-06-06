package com.lift.scheduling;

import com.lift.models.PickupRequest;

public class IdleAtAnotherFloorScheduler extends StrategyBasedScheduler {
    @Override
    public boolean scheduleLiftBasedOnStrategy(PickupRequest request, LiftSystemState systemState, SchedulingStrategy strategy) {
        return false;
    }
}
