import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


public class Monitor {
    Optional<State> currentState;

    private HashMap<String, State> states;

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
        System.out.println("DEBUG: transitioning from " + currentState.get().getName() + " to " + name + ".");
        currentState = Optional.of(states.get(name));
    }

    public void process(Event e) {
        Objects.requireNonNull(e);

        System.out.println("DEBUG: processing " + e.toString());
        currentState
                .orElseThrow(() -> new RuntimeException("No current state set (did you specify an initial state?"))
                .getHandler(e.getClass()).ifPresent(f -> f.accept(e));
    }

    public Monitor() {
        currentState = Optional.empty();
        states = new HashMap<>();
    }
}
