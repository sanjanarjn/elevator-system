package com.lift.system;

import com.lift.scheduling.LiftScheduler;
import com.lift.scheduling.LiftSystemState;
import com.lift.system.exceptions.TestingException;
import com.lift.system.models.LiftSystemTestData;
import com.lift.system.util.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;

public class LiftSchedulerTest {

    private LiftScheduler scheduler = new LiftScheduler();

    @ParameterizedTest
    @MethodSource("getTestDataForPickupRequests")
    public void testPickUpRequests(LiftSystemTestData testData) {
        LiftSystemState updatedState = scheduler.handlePickupRequest(testData.getRequest(), testData.getBeforeState());
        LiftStateValidator.validate(testData.getAfterState(), updatedState);
    }

    private static List<LiftSystemTestData> getTestDataForPickupRequests() throws TestingException {
        return TestUtil.getPickupRequestTestData("scheduler");
    }
}
