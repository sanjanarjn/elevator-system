package com.lift.scheduling;

import java.util.HashMap;
import java.util.Map;

public class StrategyBasedSchedulerFactory {

    private static Map<SchedulingStrategy, StrategyBasedScheduler> instances = new HashMap<>();

    static {
        instances.put(SchedulingStrategy.IDLE_AT_SAME_FLOOR, new IdleAtSameFloorScheduler());
        instances.put(SchedulingStrategy.MOVING_TO_THE_FLOOR, new IdleAtSameFloorScheduler());
        instances.put(SchedulingStrategy.IDLE_AT_ANOTHER_FLOOR, new IdleAtSameFloorScheduler());
    }

    public static StrategyBasedScheduler getStrategyBasedScheduler(SchedulingStrategy strategy) {
        return instances.getOrDefault(strategy, null);
    }
}
