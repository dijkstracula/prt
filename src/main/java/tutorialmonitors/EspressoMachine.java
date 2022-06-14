package tutorialmonitors;
import prt.*;

/***************************************************************************
 * This file was auto-generated on Tuesday, 14 June 2022 at 12:23:01.
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
    public record DefaultEvent() implements PObserveEvent.PEvent { }
    public record PHalt() implements PObserveEvent.PEvent { }
    public record eWarmUpReq() implements PObserveEvent.PEvent { }
    public record eGrindBeansReq() implements PObserveEvent.PEvent { }
    public record eStartEspressoReq() implements PObserveEvent.PEvent { }
    public record eStartSteamerReq() implements PObserveEvent.PEvent { }
    public record eStopSteamerReq() implements PObserveEvent.PEvent { }
    public record eGrindBeansCompleted() implements PObserveEvent.PEvent { }
    public record eEspressoCompleted() implements PObserveEvent.PEvent { }
    public record eWarmUpCompleted() implements PObserveEvent.PEvent { }
    public record eNoWaterError() implements PObserveEvent.PEvent { }
    public record eNoBeansError() implements PObserveEvent.PEvent { }
    public record eWarmerError() implements PObserveEvent.PEvent { }
    public record eEspressoButtonPressed() implements PObserveEvent.PEvent { }
    public record eSteamerButtonOff() implements PObserveEvent.PEvent { }
    public record eSteamerButtonOn() implements PObserveEvent.PEvent { }
    public record eOpenGroundsDoor() implements PObserveEvent.PEvent { }
    public record eCloseGroundsDoor() implements PObserveEvent.PEvent { }
    public record eResetCoffeeMaker() implements PObserveEvent.PEvent { }
    public record eCoffeeMakerError(int payload) implements PObserveEvent.PEvent { }
    public record eCoffeeMakerReady() implements PObserveEvent.PEvent { }
    public record eCoffeeMachineUser(long payload) implements PObserveEvent.PEvent { }
    public record eInWarmUpState() implements PObserveEvent.PEvent { }
    public record eInReadyState() implements PObserveEvent.PEvent { }
    public record eInBeansGrindingState() implements PObserveEvent.PEvent { }
    public record eInCoffeeBrewingState() implements PObserveEvent.PEvent { }
    public record eErrorHappened() implements PObserveEvent.PEvent { }
    public record eResetPerformed() implements PObserveEvent.PEvent { }

    // PMachine EspressoCoffeeMaker elided
    // PMachine CoffeeMakerControlPanel elided
    public static class EspressoMachineModesOfOperation extends Monitor {

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
