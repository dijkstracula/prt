import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class EventExperiment {

    static class AddEvent extends Event {
        int amountToAdd;
    }

    static class MulEvent extends Event {
        int amountToMul;
    }

    static class ParityMonitor {

        Optional<State> currentState;

        private HashMap<String, State> states;

        private void AddState(State s) {
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

        private void gotoState(String name) {
            Objects.requireNonNull(name);
            if (!states.containsKey(name)) {
                throw new RuntimeException("State not present");
            }
            System.out.println("DEBUG: transitioning from " + currentState.get().getName() + " to " + name + ".");
            currentState = Optional.of(states.get(name));
        }

        public void process(Event e) {
            Objects.requireNonNull(e);

            currentState
                    .orElseThrow(() -> new RuntimeException("No current state set (did you specify an initial state?"))
                    .getHandler(e.getClass()).ifPresent(f -> f.accept(e));
        }

        public ParityMonitor() {
            currentState = Optional.empty();
            states = new HashMap<>();

            AddState(new State.Builder("EvenState")
                    .isInitialState(true)
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 1) gotoState("OddState");})
                    .build());
            AddState(new State.Builder("OddState")
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 0) gotoState("EvenState");})
                    .withEvent(MulEvent.class,
                            me -> {if (me.amountToMul % 2 == 0) gotoState("EvenState");})
                    .build());
        }
    }

    public static void main(String[] args) {
        ParityMonitor pm = new ParityMonitor();

        AddEvent add1 = new AddEvent();
        add1.amountToAdd = 1;

        MulEvent dbl = new MulEvent();
        dbl.amountToMul = 2;

        Stream<Event> str = Stream.of(add1, add1, dbl, add1);
        str.forEach(pm::process);
    }
}
