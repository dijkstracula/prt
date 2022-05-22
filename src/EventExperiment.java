import java.util.Random;
import java.util.stream.Stream;

public class EventExperiment {

    record AddEvent(int amountToAdd) implements Event.Payload { }

    record MulEvent(int amountToMul) implements Event.Payload { }


    static class ParityMonitor extends Monitor<ParityMonitor.States> {
        enum States { EVEN, ODD }

        public ParityMonitor() {
            super();

            addState(new State.Builder<>(States.EVEN)
                    .isInitialState(true)
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 1) gotoState(States.ODD);})
                    .build());
            addState(new State.Builder<>(States.ODD)
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 0) gotoState(States.EVEN);})
                    .withEvent(MulEvent.class,
                            me -> {if (me.amountToMul % 2 == 0) gotoState(States.EVEN);})
                    .build());
        }
    }

    public static void main(String[] args) {
        ParityMonitor pm = new ParityMonitor();

        Random r = new Random();
        Stream<Event.Payload> payloads = Stream.generate(() -> {
                    if (r.nextBoolean()) {
                        return new AddEvent(r.nextInt(10));
                    } else {
                        return new MulEvent(r.nextInt(10));
                    }
                });
        payloads.limit(5).forEach(pm::process);
    }
}
