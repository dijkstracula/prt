package prt;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * A state in a prt.Monitor's transition diagram.  A state contains zero or more Event handlers; when the prt.Monitor
 * receives an event, it defers behaviour to the current state's handler for that event, if it exists.  (If
 * no handler exists for that particular state, the Event is simply dropped.)
 *
 * To construct a prt.State, use the `prt.State.Builder` interface.
 */
public class State {

    /**
     * Functionally-equivalent to a Consumer<T>, but may throw the checked prt.TransitionException within accept().
     * @param <T> The type to be consumed.
     */
    @FunctionalInterface
    public interface TransitionableConsumer<T> {
        /**
         * Invokes the consumer with some `t`; a `prt.TransitionException` may be thrown prior to the consumer terminating,
         * which the caller needs to handle.
         * @param t the argument to the function.
         * @throws TransitionException if invoking the function results in a state transition.
         */
        void accept(T t) throws TransitionException;
    }

    /**
     * Functionally-equivalent to a Runnable, but may throw the checked prt.TransitionException within run().
     */
    @FunctionalInterface
    public interface TransitionableRunnable {
        /**
         * Runs the Runnable; a `prt.TransitionException` may be thrown prior to the consumer terminating,
         * @throws TransitionException if invoking the function results in a state transition.
         */
        void run() throws TransitionException;
    }

    private boolean isInitialState;
    private String key;
    private HashMap<Class<? extends PObserveEvent.PEvent>, TransitionableConsumer<PObserveEvent.PEvent>> dispatch;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<TransitionableConsumer<Object>> onEntry;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Runnable> onExit;

    private State() {}

    /**
     * Returns the (uniquely-) identifying key for this prt.State, used by the prt.Monitor on state transitions.
     *
     * @return the key
     */
    public String getKey() { return key; }

    public Optional<TransitionableConsumer<Object>> getOnEntry() {
        return onEntry;
    }

    public Optional<Runnable> getOnExit() {
        return onExit;
    }

    /**
     * Returns whether or not this prt.State was marked to be the (unique) initial state of its prt.Monitor.
     *
     * @return the boolean
     */
    public boolean isInitialState() { return isInitialState; }

    @Override
    public String toString() {
        return String.format("prt.State[%s]", key);
    }

    /**
     * Returns the handler for a Payload of some given class.
     *
     * @param <P>   the subclass of `Event.Payload` whose handler we're looking up.
     * @param clazz the Java Class whose handler we're looking up.
     * @return the handler that a `P` can be called with.
     */
    public <P extends PObserveEvent.PEvent> Optional<TransitionableConsumer<PObserveEvent.PEvent>> getHandler(Class<P> clazz) {
        if (!dispatch.containsKey(clazz)) {
            return Optional.empty();
        }
        return Optional.of(dispatch.get(clazz));
    }

    /**
     * Builds a prt.State.
     */
    static public class Builder {
        private boolean isInitialState;

        private final String key;
        private final HashMap<Class<? extends PObserveEvent.PEvent>, TransitionableConsumer<PObserveEvent.PEvent>> dispatch;


        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private Optional<TransitionableConsumer<Object>> onEntry;
        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private Optional<Runnable> onExit;

        /**
         * Instantiates a new Builder.
         *
         * @param _key the uniquely-identifying key for our new prt.State.
         */
        public Builder(String _key) {
            key = _key;
            isInitialState = false;
            dispatch = new HashMap<>();
            onEntry = Optional.empty();
            onExit = Optional.empty();
        }



        /**
         * Sets whether our new prt.State should be the prt.Monitor's initial state.
         */
        public Builder isInitialState(boolean b) {
            isInitialState = b;
            return this;
        }

        /**
         * For a given `class P extends Event.Payload`, register a function `P -> void`
         * to be invoked when the prt.Monitor is currently in this state and receives an Event
         * with Payload type `P`.
         *
         * @param <P>   the subclass of Payload
         * @param clazz the subclass of Payload
         * @param f     the handler to be invoked at runtime.
         */
        public <P extends PObserveEvent.PEvent> Builder withEvent(Class<P> clazz, TransitionableConsumer<P> f) {
            Objects.requireNonNull(f);
            Objects.requireNonNull(clazz);

            if (dispatch.containsKey(clazz)) {
                throw new RuntimeException(String.format("Builder already supplied handler for Event %s", clazz.getName()));
            }
            dispatch.put(clazz, (TransitionableConsumer<PObserveEvent.PEvent>)f);
            return this;
        }

        /**
         * Register a function `P -> void` to be invoked when the prt.Monitor is currently in
         * another state and transitions to this one with some particular payload.
         *
         * Note: Payloads are untyped, and so conceivably the programmer may configure their
         * state machine to transition to the current state with a payload _other_ than P!
         * In that case, a ClassCastException will be thrown _at runtime_.
         *
         * TODO: we could simply hard-code this consumer to consume an Optional<j.l.Object>?
         * I like that less but makes the "untyped-ness" clearer to developers...
         *
         * @param f the P -> void function to invoke.
         * @return The builder
         * @param <P> The type parameter of the payload we wish to consume.
         */
        public <P> Builder withEntry(TransitionableConsumer<P> f) {
            Objects.requireNonNull(f);

            if (onEntry.isPresent()) {
                throw new RuntimeException(String.format("onEntry handler already handled for state %s",key));
            }
            onEntry = Optional.of((TransitionableConsumer<Object>) f);
            return this;
        }

        /**
         * Register a function `void -> void` to be invoked when the monitor is currently
         * in another state and transitions to this one.  If a payload was sent along with
         * the transition, it is discarded.
         *
         * @param f the void procedure to invoke.
         * @return the builder
         */
        public Builder withEntry(TransitionableRunnable f) {
            Objects.requireNonNull(f);

            if (onEntry.isPresent()) {
                throw new RuntimeException(String.format("onEntry handler already handled for state %s",key));
            }
            onEntry = Optional.of(__ -> f.run());
            return this;
        }

        public Builder withExit(Runnable f) {
            Objects.requireNonNull(f);

            if (onExit.isPresent()) {
                throw new RuntimeException(String.format("onExit handler already handled for state %s",key));
            }
            onExit = Optional.of(f);
            return this;
        }


        /**
         * Builds the new prt.State.
         *
         * @return the new prt.State
         */
        public State build() {
            State s = new State();

            s.dispatch = dispatch;
            s.isInitialState = isInitialState;
            s.key = key;
            s.onEntry = onEntry;
            s.onExit = onExit;

            return s;
        }
    }
}