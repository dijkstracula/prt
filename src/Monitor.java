import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


public class Monitor<StateEnum extends Enum<?>> {
    private Optional<State<StateEnum>> currentState;

    private final HashMap<StateEnum, State<StateEnum>> states;

    protected void addState(State<StateEnum> s) {
        Objects.requireNonNull(s);
        if (states.containsKey(s.getName())) {
            throw new RuntimeException("State already present");
        }
        states.put(s.getName(), s);

        if (s.isInitialState()) {
            if (currentState.isPresent()) {
                throw new RuntimeException("Initial state already set to " + currentState.get().getName());
            }
            currentState = Optional.of(s);
        }
    }

    protected void gotoState(StateEnum name) {
        Objects.requireNonNull(name);
        if (!states.containsKey(name)) {
            throw new RuntimeException("State not present");
        }
        System.out.println("DEBUG: transitioning from " + currentState.map(State::getName) + " to " + name + ".");
        currentState = Optional.of(states.get(name));
    }

    public void process(Event.Payload p) {
        Objects.requireNonNull(p);

        System.out.println("DEBUG: processing " + p);

        currentState
                .orElseThrow(() -> new RuntimeException("No current state set (did you specify an initial state, or is the machine halted?"))
                .getHandler(p.getClass())
                    .ifPresentOrElse(
                            f -> f.accept(p),
                            () -> System.out.println(String.format("DEBUG: No handler in %s for %s; discarding", currentState, p)));
    }

    public Monitor() {
        currentState = Optional.empty();
        states = new HashMap<>();
    }
}
