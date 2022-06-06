package com.lift.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiftState {

    private State state;
    private Integer floor;

    private Direction movingDirection;
    private Direction servingDirection;

    public LiftState() {
        this.state = State.IDLE;
        this.floor = 0;
    }
}
