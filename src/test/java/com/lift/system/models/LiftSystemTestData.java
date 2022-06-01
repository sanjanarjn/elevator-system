package com.lift.system.models;

import com.lift.models.LiftRequest;
import com.lift.models.LiftSystemState;
import com.lift.system.LiftSystemConfiguration;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LiftSystemTestData {

    LiftSystemConfiguration configuration;
    LiftSystemState beforeState;
    LiftSystemState afterState;
    LiftRequest request;
}
