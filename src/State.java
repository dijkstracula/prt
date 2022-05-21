import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class State {
    private boolean isInitialState;
    private String name;
    private HashMap<Class, Consumer<? extends Event>> dispatch;

    private State() {}

    public String getName() { return name; }

    public boolean isInitialState() { return isInitialState; }

    public <E extends Event> Optional<Consumer<Event>> getHandler(Class<E> clazz) {
        if (!dispatch.containsKey(clazz)) {
            return Optional.empty();
        }
        return Optional.of((Consumer<Event>)(dispatch.get(clazz)));
    }
    static public class Builder {
        private boolean isInitialState;

        private String name;
        private HashMap<Class, Consumer<? extends Event>> dispatch;

        public Builder(String _name) {
            name = _name;
            isInitialState = false;
            dispatch = new HashMap<>();
        }

        public Builder isInitialState(boolean b) {
            isInitialState = b;
            return this;
        }

        public <E extends Event> Builder withEvent(Class<E> clazz, Consumer<E> f) {
            Objects.requireNonNull(f);
            Objects.requireNonNull(clazz);

            if (dispatch.containsKey(clazz)) {
                throw new RuntimeException(String.format("Builder already supplied handler for Event %s", E.Name));
            }
            dispatch.put(clazz, f);
            return this;
        }

        public State build() {
            State s = new State();

            s.dispatch = dispatch;
            s.isInitialState = isInitialState;
            s.name = name;

            return s;
        }
    }
}
