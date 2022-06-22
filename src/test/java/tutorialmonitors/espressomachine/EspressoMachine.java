package tutorialmonitors.espressomachine;

/***************************************************************************
 * This file was auto-generated on Tuesday, 21 June 2022 at 16:33:13.
 * Please do not edit manually!
 **************************************************************************/

import events.PObserveEvent;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Stream;
import prt.Monitor;
import prt.RaiseEventException;
import prt.State;
import prt.TransitionException;
import prt.Values;

public class EspressoMachine {
    /* Enums */
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

    /* Events */
    public static class DefaultEvent extends PObserveEvent.PEvent<Void> {
        public DefaultEvent() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "DefaultEvent";
        } // toString()

    } // PEvent definition for DefaultEvent
    public static class PHalt extends PObserveEvent.PEvent<Void> {
        public PHalt() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "PHalt";
        } // toString()

    } // PEvent definition for PHalt
    public static class eWarmUpReq extends PObserveEvent.PEvent<Void> {
        public eWarmUpReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eWarmUpReq";
        } // toString()

    } // PEvent definition for eWarmUpReq
    public static class eGrindBeansReq extends PObserveEvent.PEvent<Void> {
        public eGrindBeansReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eGrindBeansReq";
        } // toString()

    } // PEvent definition for eGrindBeansReq
    public static class eStartEspressoReq extends PObserveEvent.PEvent<Void> {
        public eStartEspressoReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eStartEspressoReq";
        } // toString()

    } // PEvent definition for eStartEspressoReq
    public static class eStartSteamerReq extends PObserveEvent.PEvent<Void> {
        public eStartSteamerReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eStartSteamerReq";
        } // toString()

    } // PEvent definition for eStartSteamerReq
    public static class eStopSteamerReq extends PObserveEvent.PEvent<Void> {
        public eStopSteamerReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eStopSteamerReq";
        } // toString()

    } // PEvent definition for eStopSteamerReq
    public static class eGrindBeansCompleted extends PObserveEvent.PEvent<Void> {
        public eGrindBeansCompleted() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eGrindBeansCompleted";
        } // toString()

    } // PEvent definition for eGrindBeansCompleted
    public static class eEspressoCompleted extends PObserveEvent.PEvent<Void> {
        public eEspressoCompleted() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eEspressoCompleted";
        } // toString()

    } // PEvent definition for eEspressoCompleted
    public static class eWarmUpCompleted extends PObserveEvent.PEvent<Void> {
        public eWarmUpCompleted() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eWarmUpCompleted";
        } // toString()

    } // PEvent definition for eWarmUpCompleted
    public static class eNoWaterError extends PObserveEvent.PEvent<Void> {
        public eNoWaterError() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eNoWaterError";
        } // toString()

    } // PEvent definition for eNoWaterError
    public static class eNoBeansError extends PObserveEvent.PEvent<Void> {
        public eNoBeansError() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eNoBeansError";
        } // toString()

    } // PEvent definition for eNoBeansError
    public static class eWarmerError extends PObserveEvent.PEvent<Void> {
        public eWarmerError() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eWarmerError";
        } // toString()

    } // PEvent definition for eWarmerError
    public static class eEspressoButtonPressed extends PObserveEvent.PEvent<Void> {
        public eEspressoButtonPressed() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eEspressoButtonPressed";
        } // toString()

    } // PEvent definition for eEspressoButtonPressed
    public static class eSteamerButtonOff extends PObserveEvent.PEvent<Void> {
        public eSteamerButtonOff() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eSteamerButtonOff";
        } // toString()

    } // PEvent definition for eSteamerButtonOff
    public static class eSteamerButtonOn extends PObserveEvent.PEvent<Void> {
        public eSteamerButtonOn() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eSteamerButtonOn";
        } // toString()

    } // PEvent definition for eSteamerButtonOn
    public static class eOpenGroundsDoor extends PObserveEvent.PEvent<Void> {
        public eOpenGroundsDoor() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eOpenGroundsDoor";
        } // toString()

    } // PEvent definition for eOpenGroundsDoor
    public static class eCloseGroundsDoor extends PObserveEvent.PEvent<Void> {
        public eCloseGroundsDoor() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eCloseGroundsDoor";
        } // toString()

    } // PEvent definition for eCloseGroundsDoor
    public static class eResetCoffeeMaker extends PObserveEvent.PEvent<Void> {
        public eResetCoffeeMaker() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eResetCoffeeMaker";
        } // toString()

    } // PEvent definition for eResetCoffeeMaker
    public static class eCoffeeMakerError extends PObserveEvent.PEvent<Integer> {
        public eCoffeeMakerError(int p) { this.payload = p; }
        private Integer payload;
        public Integer getPayload() { return payload; }

        @Override
        public String toString() {
            return "eCoffeeMakerError[" + payload + "]";
        } // toString()

    } // PEvent definition for eCoffeeMakerError
    public static class eCoffeeMakerReady extends PObserveEvent.PEvent<Void> {
        public eCoffeeMakerReady() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eCoffeeMakerReady";
        } // toString()

    } // PEvent definition for eCoffeeMakerReady
    public static class eCoffeeMachineUser extends PObserveEvent.PEvent<Long> {
        public eCoffeeMachineUser(long p) { this.payload = p; }
        private Long payload;
        public Long getPayload() { return payload; }

        @Override
        public String toString() {
            return "eCoffeeMachineUser[" + payload + "]";
        } // toString()

    } // PEvent definition for eCoffeeMachineUser
    public static class eInWarmUpState extends PObserveEvent.PEvent<Void> {
        public eInWarmUpState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eInWarmUpState";
        } // toString()

    } // PEvent definition for eInWarmUpState
    public static class eInReadyState extends PObserveEvent.PEvent<Void> {
        public eInReadyState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eInReadyState";
        } // toString()

    } // PEvent definition for eInReadyState
    public static class eInBeansGrindingState extends PObserveEvent.PEvent<Void> {
        public eInBeansGrindingState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eInBeansGrindingState";
        } // toString()

    } // PEvent definition for eInBeansGrindingState
    public static class eInCoffeeBrewingState extends PObserveEvent.PEvent<Void> {
        public eInCoffeeBrewingState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eInCoffeeBrewingState";
        } // toString()

    } // PEvent definition for eInCoffeeBrewingState
    public static class eErrorHappened extends PObserveEvent.PEvent<Void> {
        public eErrorHappened() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eErrorHappened";
        } // toString()

    } // PEvent definition for eErrorHappened
    public static class eResetPerformed extends PObserveEvent.PEvent<Void> {
        public eResetPerformed() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "eResetPerformed";
        } // toString()

    } // PEvent definition for eResetPerformed

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
