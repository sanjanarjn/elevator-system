package com.lift.scheduling;

import com.lift.models.PickupRequest;

public class IdleAtAnotherFloorScheduler extends StrategyBasedScheduler {
    @Override
    public ScheduleUpdate scheduleLiftBasedOnStrategy(PickupRequest request, LiftSystemState systemState, SchedulingStrategy strategy) {
        return null;
    }
}
