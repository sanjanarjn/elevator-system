package com.lift.scheduling;

import com.lift.models.LiftState;
import com.lift.models.PickupRequest;
import com.lift.models.State;

import java.util.Optional;

public class IdleAtSameFloorScheduler extends StrategyBasedScheduler {
    @Override
    public boolean scheduleLiftBasedOnStrategy(PickupRequest request, LiftSystemState systemState, SchedulingStrategy strategy) {

        LiftState targetState = new LiftState();
        targetState.setFloor(request.getPickupFloor());
        targetState.setState(State.IDLE);

        LiftState updatedState = new LiftState();
        updatedState.setState(State.MOVING);
        updatedState.setMovingDirection(request.getDirection());
        updatedState.setServingDirection(request.getDirection());

        Optional<Integer> matchingLiftId = systemState.getLiftByState(targetState);
        if(matchingLiftId.isPresent()) {
            systemState.updateLiftStatus(matchingLiftId.get(), updatedState);
            return true;
        }
        else {
            return false;
        }
    }
}
