package tutorialmonitors;
import events.PObserveEvent;
import prt.*;

import java.util.Objects;

/***************************************************************************
 * This file was auto-generated on Friday, 17 June 2022 at 14:32:19.
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
    public static class DefaultEvent implements PObserveEvent.PEvent<Void> {
        public DefaultEvent() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DefaultEvent that = (DefaultEvent)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for DefaultEvent
    public static class PHalt implements PObserveEvent.PEvent<Void> {
        public PHalt() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PHalt that = (PHalt)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for PHalt
    public static class eWarmUpReq implements PObserveEvent.PEvent<Void> {
        public eWarmUpReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eWarmUpReq that = (eWarmUpReq)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eWarmUpReq
    public static class eGrindBeansReq implements PObserveEvent.PEvent<Void> {
        public eGrindBeansReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eGrindBeansReq that = (eGrindBeansReq)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eGrindBeansReq
    public static class eStartEspressoReq implements PObserveEvent.PEvent<Void> {
        public eStartEspressoReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eStartEspressoReq that = (eStartEspressoReq)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eStartEspressoReq
    public static class eStartSteamerReq implements PObserveEvent.PEvent<Void> {
        public eStartSteamerReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eStartSteamerReq that = (eStartSteamerReq)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eStartSteamerReq
    public static class eStopSteamerReq implements PObserveEvent.PEvent<Void> {
        public eStopSteamerReq() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eStopSteamerReq that = (eStopSteamerReq)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eStopSteamerReq
    public static class eGrindBeansCompleted implements PObserveEvent.PEvent<Void> {
        public eGrindBeansCompleted() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eGrindBeansCompleted that = (eGrindBeansCompleted)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eGrindBeansCompleted
    public static class eEspressoCompleted implements PObserveEvent.PEvent<Void> {
        public eEspressoCompleted() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eEspressoCompleted that = (eEspressoCompleted)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eEspressoCompleted
    public static class eWarmUpCompleted implements PObserveEvent.PEvent<Void> {
        public eWarmUpCompleted() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eWarmUpCompleted that = (eWarmUpCompleted)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eWarmUpCompleted
    public static class eNoWaterError implements PObserveEvent.PEvent<Void> {
        public eNoWaterError() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eNoWaterError that = (eNoWaterError)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eNoWaterError
    public static class eNoBeansError implements PObserveEvent.PEvent<Void> {
        public eNoBeansError() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eNoBeansError that = (eNoBeansError)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eNoBeansError
    public static class eWarmerError implements PObserveEvent.PEvent<Void> {
        public eWarmerError() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eWarmerError that = (eWarmerError)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eWarmerError
    public static class eEspressoButtonPressed implements PObserveEvent.PEvent<Void> {
        public eEspressoButtonPressed() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eEspressoButtonPressed that = (eEspressoButtonPressed)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eEspressoButtonPressed
    public static class eSteamerButtonOff implements PObserveEvent.PEvent<Void> {
        public eSteamerButtonOff() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eSteamerButtonOff that = (eSteamerButtonOff)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eSteamerButtonOff
    public static class eSteamerButtonOn implements PObserveEvent.PEvent<Void> {
        public eSteamerButtonOn() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eSteamerButtonOn that = (eSteamerButtonOn)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eSteamerButtonOn
    public static class eOpenGroundsDoor implements PObserveEvent.PEvent<Void> {
        public eOpenGroundsDoor() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eOpenGroundsDoor that = (eOpenGroundsDoor)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eOpenGroundsDoor
    public static class eCloseGroundsDoor implements PObserveEvent.PEvent<Void> {
        public eCloseGroundsDoor() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eCloseGroundsDoor that = (eCloseGroundsDoor)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eCloseGroundsDoor
    public static class eResetCoffeeMaker implements PObserveEvent.PEvent<Void> {
        public eResetCoffeeMaker() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eResetCoffeeMaker that = (eResetCoffeeMaker)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eResetCoffeeMaker
    public static class eCoffeeMakerError implements PObserveEvent.PEvent<Integer> {
        public eCoffeeMakerError(int p) { this.payload = p; }
        private Integer payload;
        public Integer getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eCoffeeMakerError that = (eCoffeeMakerError)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eCoffeeMakerError
    public static class eCoffeeMakerReady implements PObserveEvent.PEvent<Void> {
        public eCoffeeMakerReady() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eCoffeeMakerReady that = (eCoffeeMakerReady)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eCoffeeMakerReady
    public static class eCoffeeMachineUser implements PObserveEvent.PEvent<Long> {
        public eCoffeeMachineUser(long p) { this.payload = p; }
        private Long payload;
        public Long getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eCoffeeMachineUser that = (eCoffeeMachineUser)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eCoffeeMachineUser
    public static class eInWarmUpState implements PObserveEvent.PEvent<Void> {
        public eInWarmUpState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eInWarmUpState that = (eInWarmUpState)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eInWarmUpState
    public static class eInReadyState implements PObserveEvent.PEvent<Void> {
        public eInReadyState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eInReadyState that = (eInReadyState)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eInReadyState
    public static class eInBeansGrindingState implements PObserveEvent.PEvent<Void> {
        public eInBeansGrindingState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eInBeansGrindingState that = (eInBeansGrindingState)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eInBeansGrindingState
    public static class eInCoffeeBrewingState implements PObserveEvent.PEvent<Void> {
        public eInCoffeeBrewingState() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eInCoffeeBrewingState that = (eInCoffeeBrewingState)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eInCoffeeBrewingState
    public static class eErrorHappened implements PObserveEvent.PEvent<Void> {
        public eErrorHappened() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eErrorHappened that = (eErrorHappened)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eErrorHappened
    public static class eResetPerformed implements PObserveEvent.PEvent<Void> {
        public eResetPerformed() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eResetPerformed that = (eResetPerformed)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

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
