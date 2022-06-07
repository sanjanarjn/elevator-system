package com.lift.scheduling;

import com.lift.models.Lift;
import com.lift.models.LiftState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


@Setter
@Getter
@NoArgsConstructor
public class LiftSystemState {
    private List<Lift> lifts;

    public int getLiftCount() {
        return lifts == null ? 0 : lifts.size();
    }

    public void addLift(Lift lift) {
        if(this.lifts == null)
            this.lifts = new ArrayList<>();

        this.lifts.add(lift);
    }

    public Optional<Lift> getLift(int id) {
        return this.lifts == null ? Optional.empty() : lifts.stream().filter(lift -> lift.getId() == id).findFirst();
    }

    public LiftSystemState(LiftSystemState state) {
        if(state.lifts != null) {
            for (Lift lift : state.lifts) {
                this.addLift(lift);
            }
        }
    }

    void updateLiftStatus(int liftId, LiftState state) {
        Optional<Lift> liftOptional = getLift(liftId);
        if(liftOptional.isPresent()) {
            Lift lift = liftOptional.get();
            LiftState liftState = lift.getLiftState();

            if(state.getState() != null)
                liftState.setState(state.getState());

            if(state.getFloor() != null)
                liftState.setFloor(state.getFloor());

            if(state.getMovingDirection() != null)
                liftState.setMovingDirection(state.getMovingDirection());

            if(state.getServingDirection() != null)
                liftState.setServingDirection(state.getServingDirection());
        }
    }

    void addFloorToStopAt(int liftId, int floor) {
        Optional<Lift> liftOptional = getLift(liftId);
        if(liftOptional.isPresent()) {
            LiftState liftState = liftOptional.get().getLiftState();
            liftState.addFloorToStopAt(floor);
        }
    }

    Optional<Integer> getLiftByState(LiftState state) {

        if(this.lifts == null)
            return Optional.empty();

        Optional<Lift> matchingLift = lifts.stream().filter(lift -> liftMatchingStatePredicate.match(lift, state)).findFirst();
        return matchingLift.isPresent() ? Optional.of(matchingLift.get().getId()) : Optional.empty();
    }

    private static LiftMatchingStatePredicate liftMatchingStatePredicate = new LiftMatchingStatePredicate();
}
