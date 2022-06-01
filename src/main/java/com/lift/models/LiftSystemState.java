package com.lift.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Setter
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
        return this.lifts == null || lifts.size() < id ? Optional.empty() : Optional.of(lifts.get(id));
    }
}
