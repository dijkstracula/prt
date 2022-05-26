/**
 * This is meant to stub out a PObserve event that will terminate at a sink such as the Java
 * P runtime.  For monitors, the only thing of interest to us is the `Payload` field.
 * @param ts Some timestamp set by a PObserve source.
 * @param pEvent The P event of interest to us.
 */
public record PObserveEvent(int ts, PEvent pEvent) {
    public interface PEvent {}
}
