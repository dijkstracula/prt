/**
 * Thrown when a given State has no handler for a given Event.
 */
public class UnhandledEventException extends RuntimeException {
    private State state;
    private Class<? extends PObserveEvent.PEvent> clazz;

    /**
     * Instantiates a new UnhandledEventException.
     *
     * @param s the state missing some event.
     * @param c the subclass of Event.Payload without a handler.
     */
    public UnhandledEventException(State s, Class<? extends PObserveEvent.PEvent> c) {
        state = s;
        clazz = c;
    }

    @Override
    public String toString() {
        return String.format("State %s has no handler for class %s", this.state, clazz.getName());
    }
}