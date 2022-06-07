package com.lift.scheduling;

import com.lift.models.Direction;
import com.lift.models.Lift;
import com.lift.models.LiftState;
import com.lift.models.PickupRequest;

import java.util.function.Predicate;

public class MovingToTheFloorScheduler extends StrategyBasedScheduler {

    @Override
    public ScheduleUpdate scheduleLiftBasedOnStrategy(PickupRequest request, LiftSystemState systemState, SchedulingStrategy strategy) {

        int pickupFloor = request.getPickupFloor();
        Direction direction = request.getDirection();

        Predicate<Lift> movingTowardsAndServingInSameDirection = (Lift lift) -> {
            LiftState liftState = lift.getLiftState();
            boolean servingInSameDirection = lift.isServingInTheSameDirection(direction);
            boolean movingTowardsPickup = (liftState.getFloor() >= pickupFloor && Direction.DOWN.equals(liftState.getMovingDirection()))
                    || (liftState.getFloor() <= pickupFloor && Direction.UP.equals(liftState.getMovingDirection()));
            return servingInSameDirection && movingTowardsPickup;
        };

        int minDistance = Integer.MAX_VALUE;
        Lift nearestSuitableLift = null;
        for (Lift lift : systemState.getLifts()) {
            if (lift.isMoving() && movingTowardsAndServingInSameDirection.test(lift)) {
                 int distance = Math.abs(lift.getLiftState().getFloor() - pickupFloor);
                 if(distance < minDistance) {
                     distance = minDistance;
                     nearestSuitableLift = lift;
                 }
            }
        }

        if(nearestSuitableLift != null) {
            ScheduleUpdate update = new ScheduleUpdate();
            update.setSuccessfullyScheduled(true);

            LiftSystemState updatedSystemState = new LiftSystemState(systemState);
            updatedSystemState.addFloorToStopAt(nearestSuitableLift.getId(), pickupFloor);
            update.setUpdatedState(updatedSystemState);

            return update;
        }
        return ScheduleUpdate.failedUpdate();
    }
}
