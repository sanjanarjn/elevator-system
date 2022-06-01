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
    private State state;
    private int floor;

    public Lift(int id) {
        this.id = id;
        this.state = State.IDLE;
        this.floor = 0;
    }
}
