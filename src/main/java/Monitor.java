import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


/**
 * A Monitor encapsulates a state machine.
 *
 */
public class Monitor {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<State> startState;
    private State currentState;

    private final HashMap<String, State> states;

    /**
     * If the Monitor is running, new states must not be able to be added.
     * If the monitor is not running, events must not be able to be processed and states can't be transitioned.
     */
    private boolean isRunning;

    /**
     * Adds a new State to the state machine.
     *
     * @param s The state.
     */
    public void addState(State s) {
        Objects.requireNonNull(s);
        if (isRunning) {
            throw new RuntimeException("Monitor is already running; no new states may be added.");
        }

        if (states.containsKey(s.getKey())) {
            throw new RuntimeException("State already present");
        }
        states.put(s.getKey(), s);

        if (s.isInitialState()) {
            if (startState.isPresent()) {
                throw new RuntimeException("Initial state already set to " + startState.get().getKey());
            }
            startState = Optional.of(s);
        }
    }

    protected void tryAssert(boolean cond, String msg)
    {
        if (!cond) throw new PAssertionFailureException(msg);
    }

    /**
     * Transitions the Monitor to a new state, without including a payload.
     *
     * @param k the key of the state to transition to.
     *
     * @throws RuntimeException if `k` is not a state in the state machine.
     */
    protected void gotoState(String k) throws TransitionException {
        Objects.requireNonNull(k);

        if (!states.containsKey(k)) {
            throw new RuntimeException("State not present");
        }
        throw new TransitionException(states.get(k));
    }

    /**
     * Transitions the Monitor to a new state, delivering the given event afterwards.
     *
     * @param k the key of the state to transition to.
     * @param payload The payload to hand to the state entry handler.
     *
     * @throws RuntimeException if `k` is not a state in the state machine.
     */
    protected <P> void gotoState(String k, P payload) throws TransitionException {
        Objects.requireNonNull(k);
        Objects.requireNonNull(payload);

        if (!states.containsKey(k)) {
            throw new RuntimeException("State not present");
        }
        throw new TransitionException(states.get(k), payload);
    }

    /**
     * Synchronously processes one Event.Payload message.
     *
     * @param p the pEvent.
     * @throws UnhandledEventException if the pEvent's type has no associated handler.
     */
    public void process(PObserveEvent.PEvent p) throws UnhandledEventException {
        Objects.requireNonNull(p);

        if (!isRunning) {
            throw new RuntimeException("Monitor is not running (did you call ready()?)");
        }

        System.out.println("DEBUG: In " + currentState + ": processing event pEvent " + p);

        Optional<State.TransitionableConsumer<PObserveEvent.PEvent>> oc = currentState.getHandler(p.getClass());
        if (oc.isEmpty()) {
            throw new UnhandledEventException(currentState, p.getClass());
        }

        try {
            // Run the event handler, knowing that it might cause a state transition...
            oc.get().accept(p);
        } catch (TransitionException e) {
            // ...if it does, run entry/exit handlers and swap out the state.
            handleTransition(e.getTargetState(), e.getPayload());
        }
    }

    /**
     * Transitions to `s` by invoking the current state's exit handler and the new state's
     * entry handler, and updating internal bookkeeping.
     * @param s The new state.
     */
    private void handleTransition(State s, Optional<Object> payload) {
        if (!isRunning) {
            throw new RuntimeException("Monitor is not running (did you call ready()?)");
        }

        currentState.getOnExit().ifPresent(Runnable::run);
        currentState = s;

        Optional<State.TransitionableConsumer<Object>> entry = currentState.getOnEntry();
        if (entry.isPresent()) {
            State.TransitionableConsumer<Object> handler = entry.get();
            Object p = payload.orElse(null);
            try {
                handler.accept(p);
            } catch (TransitionException e2) {
                // FIXME: This isn't stack-safe.  Confirm the semantics are right and then just make a loop.
                handleTransition(e2.getTargetState(), e2.getPayload());
            } catch (ClassCastException e2) {
                throw new GotoPayloadClassException(p, currentState);
            }
        }
    }

    /**
     * Marks the Monitor as ready to run and consume events.  The initial state's entry handler will be
     * invoked.
     */
    public void ready() {
        isRunning = true;

        State s = startState.orElseThrow(() -> new RuntimeException("No initial state set (did you specify an initial state, or is the machine halted?)"));
        handleTransition(s, Optional.empty());
    }

    /**
     * Instantiates a new Monitor; users should provide domain-specific functionality in a subclass.
     */
    protected Monitor() {
        startState = Optional.empty();
        states = new HashMap<>();
        isRunning = false;

        currentState = new State.Builder("_pre_init").build();
    }


}
