package prt;

/**
 * Thrown by an event handler when execution of the handler should be interrupted
 * and restarted with a new event.
 */
public class RaiseEventException extends Exception {
    private PObserveEvent.PEvent ev;

    public PObserveEvent.PEvent getEvent() { return ev; }

    public RaiseEventException(PObserveEvent.PEvent event) {
        ev = event;
    }
}
