package com.lift.system;

import com.lift.models.Lift;
import com.lift.models.LiftState;
import com.lift.scheduling.LiftSystemState;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

public class LiftStateValidator {

    public static void validate(LiftSystemState expectedSystemState, LiftSystemState actualSystemState) {
        Assertions.assertTrue(actualSystemState != null);
        Assertions.assertEquals(expectedSystemState.getLiftCount(), actualSystemState.getLiftCount());

        int liftCount = expectedSystemState.getLiftCount();
        for (int i = 0; i < liftCount; i++) {
            Lift expectedLift = expectedSystemState.getLift(i).get();
            Optional<Lift> liftOptional = actualSystemState.getLift(i);
            if(liftOptional.isPresent()) {
                Lift actualLift = liftOptional.get();

                validateLiftState(expectedLift.getLiftState(), actualLift.getLiftState());

            }
            else {
                Assertions.fail();
            }
        }
    }

    private static void validateLiftState(LiftState expected, LiftState actual) {

        Assertions.assertEquals(expected.getState(), actual.getState());
        Assertions.assertEquals(expected.getFloor(), actual.getFloor());
    }

}
