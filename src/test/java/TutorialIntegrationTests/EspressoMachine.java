package TutorialIntegrationTests;
import prt.*;

import java.text.MessageFormat;
import java.util.*;

/***************************************************************************
 * This file was auto-generated on Friday, 10 June 2022 at 15:20:54.
 * Please do not edit manually!
 **************************************************************************/

public class EspressoMachine {
    /** Enums */
    public static class tCoffeeMakerState {
        public static final int NotWarmedUp = 0;
        public static final int Ready = 1;
        public static final int NoBeansError = 2;
        public static final int NoWaterError = 3;
    }
    public static class tCoffeeMakerOperations {
        public static final int CM_PressEspressoButton = 0;
        public static final int CM_PressSteamerButton = 1;
        public static final int CM_PressResetButton = 2;
        public static final int CM_ClearGrounds = 3;
    }

    /** Tuples */

    /** Events */
    record DefaultEvent() implements PObserveEvent.PEvent { }
    record PHalt() implements PObserveEvent.PEvent { }
    record eWarmUpReq() implements PObserveEvent.PEvent { }
    record eGrindBeansReq() implements PObserveEvent.PEvent { }
    record eStartEspressoReq() implements PObserveEvent.PEvent { }
    record eStartSteamerReq() implements PObserveEvent.PEvent { }
    record eStopSteamerReq() implements PObserveEvent.PEvent { }
    record eGrindBeansCompleted() implements PObserveEvent.PEvent { }
    record eEspressoCompleted() implements PObserveEvent.PEvent { }
    record eWarmUpCompleted() implements PObserveEvent.PEvent { }
    record eNoWaterError() implements PObserveEvent.PEvent { }
    record eNoBeansError() implements PObserveEvent.PEvent { }
    record eWarmerError() implements PObserveEvent.PEvent { }
    record eEspressoButtonPressed() implements PObserveEvent.PEvent { }
    record eSteamerButtonOff() implements PObserveEvent.PEvent { }
    record eSteamerButtonOn() implements PObserveEvent.PEvent { }
    record eOpenGroundsDoor() implements PObserveEvent.PEvent { }
    record eCloseGroundsDoor() implements PObserveEvent.PEvent { }
    record eResetCoffeeMaker() implements PObserveEvent.PEvent { }
    record eCoffeeMakerError(int payload) implements PObserveEvent.PEvent { }
    record eCoffeeMakerReady() implements PObserveEvent.PEvent { }
    record eCoffeeMachineUser(long payload) implements PObserveEvent.PEvent { }
    record eInWarmUpState() implements PObserveEvent.PEvent { }
    record eInReadyState() implements PObserveEvent.PEvent { }
    record eInBeansGrindingState() implements PObserveEvent.PEvent { }
    record eInCoffeeBrewingState() implements PObserveEvent.PEvent { }
    record eErrorHappened() implements PObserveEvent.PEvent { }
    record eResetPerformed() implements PObserveEvent.PEvent { }

    // PMachine EspressoCoffeeMaker elided
    // PMachine CoffeeMakerControlPanel elided
    static class EspressoMachineModesOfOperation extends Monitor {

        public String STARTUP_STATE = "StartUp";
        public String WARMUP_STATE = "WarmUp";
        public String READY_STATE = "Ready";
        public String BEANGRINDING_STATE = "BeanGrinding";
        public String MAKINGCOFFEE_STATE = "MakingCoffee";
        public String ERROR_STATE = "Error";


        public EspressoMachineModesOfOperation() {
            super();
            addState(new State.Builder(STARTUP_STATE)
                    .isInitialState(true)
                    .withEvent(eInWarmUpState.class, __ -> gotoState(WARMUP_STATE))
                    .build());
            addState(new State.Builder(WARMUP_STATE)
                    .isInitialState(false)
                    .withEvent(eErrorHappened.class, __ -> gotoState(ERROR_STATE))
                    .withEvent(eInReadyState.class, __ -> gotoState(READY_STATE))
                    .build());
            addState(new State.Builder(READY_STATE)
                    .isInitialState(false)
                    .withEvent(eInReadyState.class, __ -> { ; })
                    .withEvent(eInBeansGrindingState.class, __ -> gotoState(BEANGRINDING_STATE))
                    .withEvent(eErrorHappened.class, __ -> gotoState(ERROR_STATE))
                    .build());
            addState(new State.Builder(BEANGRINDING_STATE)
                    .isInitialState(false)
                    .withEvent(eInCoffeeBrewingState.class, __ -> gotoState(MAKINGCOFFEE_STATE))
                    .withEvent(eErrorHappened.class, __ -> gotoState(ERROR_STATE))
                    .build());
            addState(new State.Builder(MAKINGCOFFEE_STATE)
                    .isInitialState(false)
                    .withEvent(eInReadyState.class, __ -> gotoState(READY_STATE))
                    .withEvent(eErrorHappened.class, __ -> gotoState(ERROR_STATE))
                    .build());
            addState(new State.Builder(ERROR_STATE)
                    .isInitialState(false)
                    .withEvent(eResetPerformed.class, __ -> gotoState(STARTUP_STATE))
                    .withEvent(eErrorHappened.class, __ -> { ; })
                    .build());
        } // constructor
    } // EspressoMachineModesOfOperation monitor definition
    // PMachine SaneUser elided
    // PMachine CrazyUser elided
    // PMachine TestWithSaneUser elided
    // PMachine TestWithCrazyUser elided
} // EspressoMachine.java class definition
