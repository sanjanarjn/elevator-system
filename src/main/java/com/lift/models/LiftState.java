package com.lift.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.PriorityQueue;

@Getter
@Setter
public class LiftState {

    private State state;
    private Integer floor;
    private Direction movingDirection;
    private Direction servingDirection;

    private PriorityQueue<Integer> floorsToStopWhileGoingDown;
    private PriorityQueue<Integer> floorsToStopWhileGoingUp;

    public LiftState() {
        this.state = State.IDLE;
        this.floor = 0;
        this.floorsToStopWhileGoingUp = new PriorityQueue<>();
        this.floorsToStopWhileGoingDown = new PriorityQueue<>(Collections.reverseOrder());
    }

    public LiftState(LiftState state) {
        this();
        this.state = state.getState();
        this.floor = state.getFloor();;
        this.movingDirection = state.getMovingDirection();
        this.servingDirection = state.getServingDirection();
    }

    public void addFloorToStopAt(int floor) {
        if(Direction.UP.equals(this.movingDirection)) {
            floorsToStopWhileGoingUp.add(floor);
        }
        else {
            floorsToStopWhileGoingDown.add(floor);
        }
    }
}
