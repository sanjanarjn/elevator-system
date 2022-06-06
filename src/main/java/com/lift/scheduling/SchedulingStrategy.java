package com.lift.scheduling;

import lombok.Getter;

@Getter
public enum SchedulingStrategy {

    IDLE_AT_SAME_FLOOR(1), MOVING_TO_THE_FLOOR(2), IDLE_AT_ANOTHER_FLOOR(3);

    private int priority;

    private SchedulingStrategy(int priority) {
        this.priority = priority;
    }

    public static SchedulingStrategy getStrategyByPriority(int priority) {
        for (SchedulingStrategy strategy:
             SchedulingStrategy.values()) {
            if(strategy.getPriority() == priority)
                return strategy;
        }
        return null;
    }
}
