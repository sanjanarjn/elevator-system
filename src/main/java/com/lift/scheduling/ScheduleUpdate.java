package com.lift.scheduling;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ScheduleUpdate {

    @Setter
    private boolean successfullyScheduled;
    private LiftSystemState updatedState;

    void setUpdatedState(LiftSystemState state) {
        this.updatedState = state;
    }

    public static ScheduleUpdate failedUpdate() {
        ScheduleUpdate update = new ScheduleUpdate();
        update.setSuccessfullyScheduled(false);
        return update;
    }
}
