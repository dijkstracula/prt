import java.util.stream.Stream;

public class EventExperiment {

    record AddEvent(int amountToAdd) implements Event.Payload { }

    record MulEvent(int amountToMul) implements Event.Payload { }

    static class ParityMonitor extends Monitor {
        public ParityMonitor() {
            super();

            addState(new State.Builder("EvenState")
                    .isInitialState(true)
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 1) gotoState("OddState");})
                    .build());
            addState(new State.Builder("OddState")
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 0) gotoState("EvenState");})
                    .withEvent(MulEvent.class,
                            me -> {if (me.amountToMul % 2 == 0) gotoState("EvenState");})
                    .build());
        }
    }

    public static void main(String[] args) {
        ParityMonitor pm = new ParityMonitor();

        AddEvent add1 = new AddEvent(1);
        MulEvent dbl = new MulEvent(2);

        Stream<Event.Payload> payloads = Stream.of(add1, add1, dbl, add1);
        payloads.map(p -> new Event(42, p)).forEach(pm::process);
    }
}
