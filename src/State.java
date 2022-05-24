import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A state in a Monitor's transition diagram.  A state contains zero or more Event handlers; when the Monitor
 * receives an event, it defers behaviour to the current state's handler for that event, if it exists.  (If
 * no handler exists for that particular state, the Event is simply dropped.)
 *
 * To construct a State, use the `State.Builder` interface.
 *
 * @param <K> The type of the uniquely-identifying state key.
 */
public class State<K> {

    @FunctionalInterface
    interface InterruptibleConsumer<T> {
        void accept(T t) throws TransitionException;
    }

    private boolean isInitialState;
    private K key;
    private HashMap<Class<? extends Event.Payload>, InterruptibleConsumer<Event.Payload>> dispatch;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Consumer<Event.Payload>> onEntry;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Runnable> onExit;

    private State() {}

    /**
     * Returns the (uniquely-) identifying key for this State, used by the Monitor on state transitions.
     *
     * @return the key
     */
    public K getKey() { return key; }

    public Optional<Consumer<Event.Payload>> getOnEntry() {
        return onEntry;
    }

    public Optional<Runnable> getOnExit() {
        return onExit;
    }

    /**
     * Returns whether or not this State was marked to be the (unique) initial state of its Monitor.
     *
     * @return the boolean
     */
    public boolean isInitialState() { return isInitialState; }

    @Override
    public String toString() {
        return String.format("State[%s]", key);
    }

    /**
     * Returns the handler for a Payload of some given class.
     *
     * @param <P>   the subclass of `Event.Payload` whose handler we're looking up.
     * @param clazz the Java Class whose handler we're looking up.
     * @return the handler that a `P` can be called with.
     */
    public <P extends Event.Payload> Optional<InterruptibleConsumer<Event.Payload>> getHandler(Class<P> clazz) {
        if (!dispatch.containsKey(clazz)) {
            return Optional.empty();
        }
        return Optional.of(dispatch.get(clazz));
    }

    /**
     * Builds a State.
     *
     * @param <K> The type of the uniquely-identifying state key.
     */
    static public class Builder<K> {
        private boolean isInitialState;

        private final K key;
        private final HashMap<Class<? extends Event.Payload>, InterruptibleConsumer<Event.Payload>> dispatch;


        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private Optional<Consumer<Event.Payload>> onEntry;
        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private Optional<Runnable> onExit;

        /**
         * Instantiates a new Builder.
         *
         * @param _key the uniquely-identifying key for our new State.
         */
        public Builder(K _key) {
            key = _key;
            isInitialState = false;
            dispatch = new HashMap<>();
            onEntry = Optional.empty();
            onExit = Optional.empty();
        }



        /**
         * Sets whether our new State should be the Monitor's initial state.
         */
        public Builder<K> isInitialState(boolean b) {
            isInitialState = b;
            return this;
        }

        /**
         * For a given `class P extends Event.Payload`, register a function `P -> void` with the
         * class to be invoked when the Monitor is currently in this state and receives an Event
         * with Payload type `P`.
         *
         * @param <P>   the subclass of Payload
         * @param clazz the subclass of Payload
         * @param f     the handler to be invoked at runtime.
         */
        public <P extends Event.Payload> Builder<K> withEvent(Class<P> clazz, InterruptibleConsumer<P> f) {
            Objects.requireNonNull(f);
            Objects.requireNonNull(clazz);

            if (dispatch.containsKey(clazz)) {
                throw new RuntimeException(String.format("Builder already supplied handler for Event %s", clazz.getName()));
            }
            dispatch.put(clazz, (InterruptibleConsumer<Event.Payload>)f);
            return this;
        }

        public Builder<K> withEntry(Consumer<Event.Payload> f) {
            Objects.requireNonNull(f);

            if (onEntry.isPresent()) {
                throw new RuntimeException(String.format("onEntry handler already handled for state %s",key));
            }
            onEntry = Optional.of(f);
            return this;
        }

        public Builder<K> withExit(Runnable f) {
            Objects.requireNonNull(f);

            if (onExit.isPresent()) {
                throw new RuntimeException(String.format("onExit handler already handled for state %s",key));
            }
            onExit = Optional.of(f);
            return this;
        }


        /**
         * Builds the new State.
         *
         * @return the new State
         */
        public State<K> build() {
            State<K> s = new State<>();

            s.dispatch = dispatch;
            s.isInitialState = isInitialState;
            s.key = key;
            s.onEntry = onEntry;
            s.onExit = onExit;

            return s;
        }
    }
}
