package events;

/**
 * This is meant to stub out a PObserve event that will terminate at a sink such as the Java
 * P runtime.  For monitors and the runtime in general, the only thing of interest to us is
 * the `PEvent` field.
 * @param ts Some timestamp set by a PObserve source.
 * @param pEvent The P event of interest to us.
 */
public record PObserveEvent(TimestampInterval ts, PEvent pEvent) {
    public interface PEvent {
    }
}
