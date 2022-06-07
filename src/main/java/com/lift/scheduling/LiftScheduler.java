package com.lift.scheduling;

import com.lift.models.PickupRequest;

public class LiftScheduler {

    public LiftSystemState handlePickupRequest(PickupRequest request, LiftSystemState systemState) {

        SchedulingStrategy[] strategies = SchedulingStrategy.values();
        int numStrategies = strategies.length;

        ScheduleUpdate scheduleUpdate = null;
        for (int i = 1; i <= numStrategies; i++) {
            SchedulingStrategy strategy = SchedulingStrategy.getStrategyByPriority(i);
            StrategyBasedScheduler strategyBasedScheduler = StrategyBasedSchedulerFactory.getStrategyBasedScheduler(strategy);
            scheduleUpdate = strategyBasedScheduler.scheduleLiftBasedOnStrategy(request, systemState, strategy);
            if(scheduleUpdate.isSuccessfullyScheduled()) {
                break;
            }
        }
        return scheduleUpdate.getUpdatedState();
    }
}
