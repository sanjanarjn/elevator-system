package com.lift.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiftRequest {

    private Direction direction;
    private int pickupFloor;
    private int dropFloor;
}
