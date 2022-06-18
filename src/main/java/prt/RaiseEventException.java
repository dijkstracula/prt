package prt;

import events.PObserveEvent;

/**
 * Thrown by an event handler when execution of the handler should be interrupted
 * and restarted with a new event.
 */
public class RaiseEventException extends Exception {
    // XXX: We downcast to an Object since a Throwable cannot take type parameters.
    private final PObserveEvent.PEvent<Object> ev;

    public PObserveEvent.PEvent<Object> getEvent() { return ev; }

    public RaiseEventException(PObserveEvent.PEvent<Object> event) {
        ev = event;
    }
}
