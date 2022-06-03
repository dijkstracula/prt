import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prt.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * This prt.Monitor has one piece of ghost state: a counter that can be incremented by
     * processing events.
     */
    class CounterMonitor extends Monitor {
        record AddEvent(int amountToAdd) implements PObserveEvent.PEvent { }

        private String INIT_STATE= "Init";

        public int count;

        public CounterMonitor() {
            super();
            count = 0;

            addState(new State.Builder(INIT_STATE)
                    .isInitialState(true)
                    .withEvent(AddEvent.class, addEvent -> count += addEvent.amountToAdd)
                    .build());
        }
    }

    class ChainedEntryHandlerMonitor extends Monitor {
        private String A_STATE = "A";
        private String B_STATE = "B";
        private String C_STATE = "C";

        public List<String> stateAcc; // We'll use this to track what states we've transitioned through.
        public ChainedEntryHandlerMonitor() {
            super();

            stateAcc = new ArrayList<>();

            addState(new State.Builder(A_STATE)
                    .isInitialState(true)
                    .withEntry(() -> gotoState(B_STATE))
                    .withExit(() -> stateAcc.add(A_STATE))
                    .build());
            addState(new State.Builder(B_STATE)
                    .withEntry(() -> gotoState(C_STATE))
                    .withExit(() -> stateAcc.add(B_STATE))
                    .build());
            addState(new State.Builder(C_STATE)
                    .withEntry(() -> stateAcc.add(C_STATE))
                    .build());
        }
    }

    class GotoStateWithPayloadsMonitor extends Monitor {
        private String A_STATE = "A";
        private String B_STATE = "B";
        private String C_STATE = "C";

        public List<Object> eventsProcessed; // We'll use this to track what events we've processed

        public GotoStateWithPayloadsMonitor() {
            super();

            eventsProcessed = new ArrayList<>();

            addState(new State.Builder(A_STATE)
                    .isInitialState(true)
                    .withEntry(() -> {
                        gotoState(B_STATE, "Hello from prt.State A");
                    })
                    .build());
            addState(new State.Builder(B_STATE)
                    .withEntry(s -> {
                        eventsProcessed.add(s);
                        gotoState(C_STATE, "Hello from prt.State B");
                    })
                    .build());
            addState(new State.Builder(C_STATE)
                    .withEntry(s -> eventsProcessed.add(s))
                    .build());
        }
    }


    class GotoStateWithIllTypedPayloadsMonitor extends Monitor {
        private String A_STATE = "A";
        private String B_STATE = "B";

        public List<String> eventsProcessed; // We'll use this to track what events we've processed

        public GotoStateWithIllTypedPayloadsMonitor() {
            super();

            eventsProcessed = new ArrayList<>();

            addState(new State.Builder(A_STATE)
                    .isInitialState(true)
                    .withEntry(() -> gotoState(B_STATE, Integer.valueOf(42)))
                    .build());
            addState(new State.Builder(B_STATE)
                    .withEntry((String s) -> eventsProcessed.add(s))
                    .build());
        }
    }

    /**
     * This monitor immediately asserts.
     */
    class ImmediateAssertionMonitor extends Monitor {
        private String INIT_STATE = "Init";

        public ImmediateAssertionMonitor() {
            super();
            addState(new State.Builder(INIT_STATE)
                    .isInitialState(true)
                    .withEntry(() -> tryAssert(1 > 2, "Math works"))
                    .build());
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
    void testNonUniqueStateKeyConstruction() {
        Throwable e;
        e = assertThrows(RuntimeException.class, () -> new NonUniqueStateKeyMonitor().ready());
        assertTrue(e.getMessage().contains("prt.State already present"));
    }

    @Test
    @DisplayName("Monitors must be ready()ied before events can be processed")
    void testNonReadyMonitors() {
        CounterMonitor m = new CounterMonitor();
        Throwable e = assertThrows(RuntimeException.class, () -> m.process(new CounterMonitor.AddEvent(42)));
        assertTrue(e.getMessage().contains("not running"));
    }

    @Test
    @DisplayName("prt.Monitor can process ghost state-mutating events")
    void testStateMutationOnEvent() {
        CounterMonitor m = new CounterMonitor();
        m.ready();

        assertEquals(m.count, 0);
        m.process(new CounterMonitor.AddEvent(1));
        m.process(new CounterMonitor.AddEvent(2));
        m.process(new CounterMonitor.AddEvent(3));
        assertEquals(m.count, 6);
    }

    @Test
    @DisplayName("Chained gotos in entry handlers work")
    void testChainedEntryHandlers() {
        ChainedEntryHandlerMonitor m = new ChainedEntryHandlerMonitor();
        m.ready();

        assertTrue(m.stateAcc.equals(List.of("A", "B", "C")));
    }

    @Test
    @DisplayName("Payloads can be passed to entry handlers")
    void testChainedEntryHandlersWithPayloads() {
        GotoStateWithPayloadsMonitor m = new GotoStateWithPayloadsMonitor();
        m.ready();

        assertTrue(m.eventsProcessed.equals(List.of("Hello from prt.State A", "Hello from prt.State B")));
    }

    @Test
    @DisplayName("Ill-typed payload handlers throw")
    void testChainedEntryHandlersWithIllTypedPayloads() {
        GotoStateWithIllTypedPayloadsMonitor m = new GotoStateWithIllTypedPayloadsMonitor();

        assertThrows(GotoPayloadClassException.class, () -> m.ready());
    }

    @Test
    @DisplayName("Assertion failures throw")
    void testAssertionFailureDoesThrow() {
        ImmediateAssertionMonitor m = new ImmediateAssertionMonitor();
        Throwable e = assertThrows(PAssertionFailureException.class,
                () -> m.ready(), "Assertion failed: Math works");
    }
}
