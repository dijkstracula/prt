import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class State {
    private boolean isInitialState;
    private String name;
    private HashMap<Class<? extends Event.Payload>, Consumer<Event.Payload>> dispatch;

    private State() {}

    public String getName() { return name; }

    public boolean isInitialState() { return isInitialState; }

    public <P extends Event.Payload> Optional<Consumer<Event.Payload>> getHandler(Class<P> clazz) {
        if (!dispatch.containsKey(clazz)) {
            return Optional.empty();
        }
        return Optional.of(dispatch.get(clazz));
    }
    static public class Builder {
        private boolean isInitialState;

        private final String name;
        private final HashMap<Class<? extends Event.Payload>, Consumer<Event.Payload>> dispatch;

        public Builder(String _name) {
            name = _name;
            isInitialState = false;
            dispatch = new HashMap<>();
        }

        public Builder isInitialState(boolean b) {
            isInitialState = b;
            return this;
        }

        public <P extends Event.Payload> Builder withEvent(Class<P> clazz, Consumer<P> f) {
            Objects.requireNonNull(f);
            Objects.requireNonNull(clazz);

            if (dispatch.containsKey(clazz)) {
                throw new RuntimeException(String.format("Builder already supplied handler for Event %s", clazz.getName()));
            }
            dispatch.put(clazz, (Consumer<Event.Payload>)f);
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
