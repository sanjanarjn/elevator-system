package com.lift.system;

import com.lift.models.Lift;
import com.lift.models.LiftState;
import com.lift.models.PickupRequest;
import com.lift.scheduling.LiftSystemState;
import com.lift.scheduling.LiftScheduler;
import com.lift.system.exceptions.TestingException;
import com.lift.system.models.LiftSystemTestData;
import com.lift.system.util.TestUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LiftSystemTest {

    @InjectMocks
    private LiftSystem liftSystem = new LiftSystem();

    @Mock
    private LiftScheduler scheduler;

    private LiftSystemTestData initialTestData;

    @BeforeEach
    public void initialiseSystem() throws TestingException {
        initialTestData = TestUtil.getTestDataForInitialisation();
        liftSystem.initialize(initialTestData.getConfiguration());

        scheduler = Mockito.mock(LiftScheduler.class);
    }

    @Test
    @Order(1)
    public void testInitialStateOfSystem() {
        LiftStateValidator.validate(initialTestData.getAfterState(), liftSystem.getSystemState());
    }

    @Test
    public void testInitialPickupRequestFromUser() throws TestingException {

        LiftSystemTestData testData = TestUtil.getTestDataForInitialRequest();
        PickupRequest request = testData.getRequest();

        liftSystem.acceptPickupRequest(request);

        verify(scheduler, atMostOnce()).handlePickupRequest(request, liftSystem.getSystemState());
    }
}

