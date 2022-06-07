package com.lift.scheduling;

import com.lift.models.Direction;
import com.lift.models.LiftState;
import com.lift.models.PickupRequest;
import com.lift.models.State;

import java.util.Optional;

public class IdleAtSameFloorScheduler extends StrategyBasedScheduler {
    @Override
    public ScheduleUpdate scheduleLiftBasedOnStrategy(PickupRequest request, LiftSystemState systemState, SchedulingStrategy strategy) {

        Direction direction = request.getDirection();

        LiftState targetState = new LiftState();
        int pickupFloor = request.getPickupFloor();

        targetState.setFloor(pickupFloor);
        targetState.setState(State.IDLE);

        LiftState updatedState = new LiftState();
        updatedState.setState(State.MOVING);

        updatedState.setMovingDirection(direction);
        updatedState.setServingDirection(direction);

        Optional<Integer> matchingLiftId = systemState.getLiftByState(targetState);
        if(matchingLiftId.isPresent()) {
            ScheduleUpdate update = new ScheduleUpdate();
            update.setSuccessfullyScheduled(true);

            int liftId = matchingLiftId.get();

            LiftSystemState updatedSystemState = new LiftSystemState(systemState);
            updatedSystemState.updateLiftStatus(liftId, updatedState);
            updatedSystemState.addFloorToStopAt(liftId, pickupFloor);
            update.setUpdatedState(updatedSystemState);

            return update;
        }
        else {
            return ScheduleUpdate.failedUpdate();
        }
    }
}
