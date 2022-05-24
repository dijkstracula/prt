/**
 * A TransitionException is raised by user handlers when they would like to transition
 * to a new state.
 *
 * Internal note: Java doesn't let us override j.l.Throwable with a parameterized subtype,
 * which I didn't know until just now!  For the moment, we are doing some unchecked casts
 * to get around the fact that we can't specify the StateKey type.  I wonder if that bit
 * of polymorphism is more trouble than it's worth and we should simply use String keys.
 */
public class TransitionException extends Exception {
    private State newState;

    public State getNewState() {
        return newState;
    }

    public TransitionException(State s) {
        this.newState = s;
    }
}
