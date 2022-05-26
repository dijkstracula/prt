import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


/**
 * A Monitor encapsulates a state machine.
 *
 */
public class Monitor {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<State> currentState;

    private final HashMap<String, State> states;

    /**
     * Adds a new State to the state machine.
     *
     * @param s The state.
     */
    protected void addState(State s) {
        Objects.requireNonNull(s);
        if (states.containsKey(s.getKey())) {
            throw new RuntimeException("State already present");
        }
        states.put(s.getKey(), s);

        if (s.isInitialState()) {
            if (currentState.isPresent()) {
                throw new RuntimeException("Initial state already set to " + currentState.get().getKey());
            }
            currentState = Optional.of(s);
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
        if (!states.containsKey(k)) {
            throw new RuntimeException("State not present");
        }
        //System.out.println("DEBUG: transitioning from " + currentState.map(State::toString).orElse("???") + " to " + k + ".");
        throw new TransitionException(states.get(k));
    }

    /**
     * Synchronously processes one Event.Payload message.
     *
     * @param p the payload.
     * @throws UnhandledEventException if the payload's type has no associated handler.
     */
    public void process(Event.Payload p) throws UnhandledEventException {
        Objects.requireNonNull(p);

        State s = currentState.orElseThrow(() -> new RuntimeException("No current state set (did you specify an initial state, or is the machine halted?)"));

        System.out.println("DEBUG: In " + s + ": processing event payload " + p);

        Optional<State.InterruptibleConsumer<Event.Payload>> oc = s.getHandler(p.getClass());
        if (oc.isEmpty()) {
            throw new UnhandledEventException(s, p.getClass());
        }

        try {
            // Run the event handler, knowing that it might cause a state transition...
            oc.get().accept(p);
        } catch (TransitionException e) {
            // ...if it does, run entry/exit handlers and swap out the state.

            State next = e.getNewState();

            s.getOnExit().ifPresent(Runnable::run);
            next.getOnEntry().ifPresent(f -> f.accept(p));

            currentState = Optional.of(next);
        }
    }

    /**
     * Instantiates a new Monitor; users should provide domain-specific functionality in a subclass.
     */
    protected Monitor() {
        currentState = Optional.empty();

        states = new HashMap<>();
    }


}
