package com.lift.system;

import com.lift.models.Direction;
import com.lift.models.Lift;
import com.lift.models.LiftRequest;
import com.lift.models.LiftSystemState;
import com.lift.system.exceptions.TestingException;
import com.lift.system.models.LiftSystemTestData;
import com.lift.system.util.TestUtil;
import org.junit.jupiter.api.*;

import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LiftSystemTest {

    private static LiftSystem liftSystem = new LiftSystem();

    @Test
    @Order(1)
    public void testInitialiseSystem() throws TestingException {
        LiftSystemTestData testData = TestUtil.getTestDataForInitialisation();

        liftSystem.initialize(testData.getConfiguration());
        validate(testData.getAfterState(), liftSystem.getSystemState());
    }

    private void validate(LiftSystemState expectedSystemState, LiftSystemState actualSystemState) {
        Assertions.assertTrue(actualSystemState != null);
        Assertions.assertEquals(expectedSystemState.getLiftCount(), actualSystemState.getLiftCount());

        int liftCount = expectedSystemState.getLiftCount();
        for (int i = 0; i < liftCount; i++) {
            Lift expectedLift = expectedSystemState.getLift(i).get();
            Optional<Lift> liftOptional = actualSystemState.getLift(i);
            if(liftOptional.isPresent()) {
                Lift actualLift = liftOptional.get();
                Assertions.assertEquals(expectedLift.getState(), actualLift.getState());
                Assertions.assertEquals(expectedLift.getFloor(), expectedLift.getFloor());
            }
            else {
                Assertions.fail();
            }
        }
    }

    @Test
    @Order(2)
    public void testInitialLiftRequestFromUser() throws TestingException {

        LiftSystemTestData testData = TestUtil.getTestDataForInitialRequest();
        LiftRequest request = testData.getRequest();

        liftSystem.handleRequest(request);

        validate(testData.getAfterState(), liftSystem.getSystemState());
    }
}
