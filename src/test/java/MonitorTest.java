import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonitorTest {
    /**
     * This monitor has no default state; an exception should be raised when .ready() is called.
     */
    class NoDefaultStateMonitor extends Monitor {
        private String INIT_STATE= "Init";
        public NoDefaultStateMonitor() {
            super();
            addState(new State.Builder(INIT_STATE).build());
        }
    }

    /**
     * This monitor has two default states; an exception will be thrown in the second addState().
     */
    class MultipleDefaultStateMonitors extends Monitor {
        private String INIT_STATE= "Init";
        private String OTHER_STATE= "Other";
        public MultipleDefaultStateMonitors() {
            super();
            addState(new State.Builder(INIT_STATE).isInitialState(true).build());
            addState(new State.Builder(OTHER_STATE).isInitialState(true).build());
        }
    }

    /**
     * This monitor has two states with the same key; an exception will be thrown in the second addState().
     */
    class NonUniqueStateKeyMonitor extends Monitor {
        private String INIT_STATE= "Init";
        private String OTHER_STATE= "Other";
        public NonUniqueStateKeyMonitor() {
            super();
            addState(new State.Builder(INIT_STATE).isInitialState(true).build());
            addState(new State.Builder(INIT_STATE).isInitialState(true).build());
        }
    }

    @Test
    @DisplayName("Monitors require exactly one default state")
    void testDefaultStateConstruction() {
        Throwable e;

        e = assertThrows(RuntimeException.class, () -> new NoDefaultStateMonitor().ready());
        assertTrue(e.getMessage().contains("No initial state set"));

        e = assertThrows(RuntimeException.class, () -> new MultipleDefaultStateMonitors().ready());
        assertTrue(e.getMessage().contains("Initial state already set"));
    }

    @Test
    @DisplayName("Monitors require unique states")
    void testNonUniqueStateKeyConstruction()
    {
        Throwable e;
        e = assertThrows(RuntimeException.class, () -> new NonUniqueStateKeyMonitor().ready());
        assertTrue(e.getMessage().contains("State already present"));
    }
}
