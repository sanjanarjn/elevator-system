package com.lift.scheduling;

import com.lift.models.PickupRequest;

public class LiftScheduler {

    public void handlePickupRequest(PickupRequest request, LiftSystemState systemState) {

        SchedulingStrategy[] strategies = SchedulingStrategy.values();
        int numStrategies = strategies.length;

        for (int i = 1; i <= numStrategies; i++) {
            SchedulingStrategy strategy = SchedulingStrategy.getStrategyByPriority(i);
            StrategyBasedScheduler strategyBasedScheduler = StrategyBasedSchedulerFactory.getStrategyBasedScheduler(strategy);
            boolean liftScheduled = strategyBasedScheduler.scheduleLiftBasedOnStrategy(request, systemState, strategy);
            if(liftScheduled) {
                break;
            }
        }
    }
}
