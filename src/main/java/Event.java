public record Event(int ts, Event.Payload payload) {
    public interface Payload {}
}
