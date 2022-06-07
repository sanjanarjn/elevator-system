package com.lift.system;

import com.lift.models.Lift;
import com.lift.models.LiftState;
import com.lift.scheduling.LiftSystemState;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class LiftStateValidator {

    public static void validate(LiftSystemState expectedSystemState, LiftSystemState actualSystemState) {
        Assertions.assertTrue(actualSystemState != null);
        Assertions.assertEquals(expectedSystemState.getLiftCount(), actualSystemState.getLiftCount());

        List<Lift> expectedLifts = expectedSystemState.getLifts();
        List<Lift> actualLifts = actualSystemState.getLifts();

        for (int i = 0; i < expectedLifts.size(); i++) {
            Lift expectedLift = expectedLifts.get(i);
            Lift actualLift = actualLifts.get(i);
            validateLiftState(expectedLift.getLiftState(), actualLift.getLiftState());
        }
    }

    private static void validateLiftState(LiftState expected, LiftState actual) {

        Assertions.assertEquals(expected.getState(), actual.getState());
        Assertions.assertEquals(expected.getFloor(), actual.getFloor());

        validateFloorsToStopAt(expected.getFloorsToStopWhileGoingUp(), actual.getFloorsToStopWhileGoingUp());
        validateFloorsToStopAt(expected.getFloorsToStopWhileGoingDown(), actual.getFloorsToStopWhileGoingDown());
    }

    private static void validateFloorsToStopAt(PriorityQueue<Integer> expectedfloorsToStopWhileGoingUp, PriorityQueue<Integer> actualFloorsToStopWhileGoingUp) {
        Assertions.assertEquals(expectedfloorsToStopWhileGoingUp.size(), actualFloorsToStopWhileGoingUp.size());
        int floorsToStopCount = expectedfloorsToStopWhileGoingUp.size();
        for (int i = 0; i < floorsToStopCount; i++) {
            Assertions.assertEquals(expectedfloorsToStopWhileGoingUp.remove(), actualFloorsToStopWhileGoingUp.remove());
        }
    }

}
