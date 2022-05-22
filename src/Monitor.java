import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


public class Monitor {
    Optional<State> currentState;

    private final HashMap<String, State> states;

    protected void addState(State s) {
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

    protected void gotoState(String name) {
        Objects.requireNonNull(name);
        if (!states.containsKey(name)) {
            throw new RuntimeException("State not present");
        }
        System.out.println("DEBUG: transitioning from " + currentState.map(State::getName).orElse("???") + " to " + name + ".");
        currentState = Optional.of(states.get(name));
    }

    public void process(Event e) {
        Objects.requireNonNull(e);

        Event.Payload p = e.payload();

        System.out.println("DEBUG: processing " + e);

        currentState
                .orElseThrow(() -> new RuntimeException("No current state set (did you specify an initial state?"))
                .getHandler(p.getClass()).ifPresent(f -> f.accept(p));
    }

    public Monitor() {
        currentState = Optional.empty();
        states = new HashMap<>();
    }
}
