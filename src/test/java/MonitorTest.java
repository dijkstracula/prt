import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonitorTest {
    /**
     * This monitor has no default state; an exception should be raised if it's constructed.
     */
    class NoDefaultStateMonitor extends Monitor {
        private String INIT_STATE= "Init";

        public NoDefaultStateMonitor() {
            super();
            addState(new State.Builder(INIT_STATE)
                    .build());
        }
    }

    @Test
    @DisplayName("Monitors require exactly one default state")
    void testDefaultStateConstruction() {
        Throwable e = assertThrows(RuntimeException.class,
                () -> new NoDefaultStateMonitor());

        assertTrue(e.getMessage().contains("No current state set"));
    }
}
