public abstract class Event {
    // TODO: who populates the timestamp?  It's not currently set in
    // the subclass' constructors.
    // Should we contemplate factoring out the payload again??
    protected int ts;
}
