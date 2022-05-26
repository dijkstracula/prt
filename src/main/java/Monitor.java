import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


/**
 * A Monitor encapsulates a state machine.
 *
 */
public class Monitor {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<State> initialState;
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
            if (initialState.isPresent()) {
                throw new RuntimeException("Initial state already set to " + initialState.get().getKey());
            }
            initialState = Optional.of(s);
        }
    }


    /**
     * Transitions the Monitor to a new state.
     *
     * @param k the key of the state to transition to.
     * @throws RuntimeException if `k` is not a state in the state machine.
     */
    protected void gotoState(String k) throws TransitionException {
        Objects.requireNonNull(k);

        if (!isRunning) {
            throw new RuntimeException("Monitor is not running (did you call ready()?)");
        }

        if (!states.containsKey(k)) {
            throw new RuntimeException("State not present");
        }
        //System.out.println("DEBUG: transitioning from " + currentState.map(State::toString).orElse("???") + " to " + k + ".");
        throw new TransitionException(states.get(k));
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

        Optional<State.InterruptibleConsumer<PObserveEvent.PEvent>> oc = currentState.getHandler(p.getClass());
        if (oc.isEmpty()) {
            throw new UnhandledEventException(currentState, p.getClass());
        }

        try {
            // Run the event handler, knowing that it might cause a state transition...
            oc.get().accept(p);
        } catch (TransitionException e) {
            // ...if it does, run entry/exit handlers and swap out the state.

            State next = e.getNewState();

            currentState.getOnExit().ifPresent(Runnable::run);
            next.getOnEntry().ifPresent(f -> f.accept(p));

            currentState = next;
        }
    }

    public void ready() {
        State s = initialState.orElseThrow(() -> new RuntimeException("No initial state set (did you specify an initial state, or is the machine halted?)"));
        isRunning = true;
        currentState = s;
    }

    /**
     * Instantiates a new Monitor; users should provide domain-specific functionality in a subclass.
     */
    protected Monitor() {
        initialState = Optional.empty();
        states = new HashMap<>();
        isRunning = false;
    }


}
