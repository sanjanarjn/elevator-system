package com.lift.scheduling;

import com.lift.models.Lift;
import com.lift.models.LiftState;

@FunctionalInterface
interface LiftStatePredicate {
    boolean match(Lift lift, LiftState liftState);
}

class LiftMatchingStatePredicate implements LiftStatePredicate {

    @Override
    public boolean match(Lift lift, LiftState state) {

        boolean matching = true;
        LiftState liftState = lift.getLiftState();
        if(state.getState() != null) {
            matching = state.getState().equals(liftState.getState());
        }

        if(!matching)
            return false;

        if(state.getFloor() != null) {
            matching = state.getFloor().equals(liftState.getFloor());
        }

        if(!matching)
            return false;

        if(state.getServingDirection() != null) {
            matching = state.getServingDirection().equals(liftState.getServingDirection());
        }
        return matching;
    }
}


