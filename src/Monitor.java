import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


/**
 * A Monitor encapsulates a state machine.
 *
 * @param <StateKey> The type of identifier used to distinguish different states in event handlers when transitions happen.
 */
public class Monitor<StateKey> {
    private Optional<State<StateKey>> currentState;

    private final HashMap<StateKey, State<StateKey>> states;

    /**
     * Adds a new State to the state machine.
     *
     * @param s The state.
     */
    protected void addState(State<StateKey> s) {
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
    protected void gotoState(StateKey k) {
        Objects.requireNonNull(k);
        if (!states.containsKey(k)) {
            throw new RuntimeException("State not present");
        }
        System.out.println("DEBUG: transitioning from " + currentState.map(State::toString).orElse("???") + " to " + k + ".");
        currentState = Optional.of(states.get(k));
    }

    /**
     * Synchronously processes one Event.Payload message.
     *
     * @param p the payload.
     */
    public void process(Event.Payload p) {
        Objects.requireNonNull(p);

        System.out.println("DEBUG: In " + currentState.map(State::toString).orElse("???") + ": processing event payload " + p);

        currentState
                .orElseThrow(() -> new RuntimeException("No current state set (did you specify an initial state, or is the machine halted?)"))
                .getHandler(p.getClass())
                    .ifPresentOrElse(
                            f -> f.accept(p),
                            () -> System.out.println(String.format("DEBUG: No handler in %s for %s; discarding", currentState, p)));
    }

    /**
     * Instantiates a new Monitor; users should provide domain-specific functionality in a subclass.
     */
    protected Monitor() {
        currentState = Optional.empty();
        states = new HashMap<>();
    }
}
