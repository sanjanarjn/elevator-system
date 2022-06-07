package com.lift.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lift {

    private int id;
    private LiftState liftState;

    public Lift(int id) {
        this.id = id;
        this.liftState = new LiftState();
    }

    public Lift(Lift other) {

        this.id = other.id;
        if(other.getLiftState() != null) {
            this.setLiftState(new LiftState(other.getLiftState()));
        }
    }
}
