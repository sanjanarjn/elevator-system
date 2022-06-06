package com.lift.system.models;

import com.lift.models.PickupRequest;
import com.lift.scheduling.LiftSystemState;
import com.lift.system.LiftSystemConfiguration;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LiftSystemTestData {

    LiftSystemConfiguration configuration;
    LiftSystemState beforeState;
    LiftSystemState afterState;
    PickupRequest request;
}
