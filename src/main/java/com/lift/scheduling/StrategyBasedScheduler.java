package com.lift.scheduling;

import com.lift.models.PickupRequest;

public abstract class StrategyBasedScheduler {

    public abstract ScheduleUpdate scheduleLiftBasedOnStrategy(PickupRequest request, LiftSystemState systemState, SchedulingStrategy strategy);
}
