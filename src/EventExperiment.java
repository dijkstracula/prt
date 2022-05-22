import java.util.Random;
import java.util.stream.Stream;

public class EventExperiment {

    enum ParityStates { EVEN, ODD }

    record AddEvent(int amountToAdd) implements Event.Payload { }
    record MulEvent(int amountToMul) implements Event.Payload { }


    static class ParityMonitor extends Monitor<ParityStates> {
        public ParityMonitor() {
            super();

            addState(new State.Builder<>(ParityStates.EVEN)
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 1) gotoState(ParityStates.ODD);})
                    .build());
            addState(new State.Builder<>(ParityStates.ODD)
                    .isInitialState(true)
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 1) gotoState(ParityStates.EVEN);})
                    .withEvent(MulEvent.class,
                            me -> {if (me.amountToMul % 2 == 0) gotoState(ParityStates.EVEN);})
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
        payloads.limit(10).forEach(pm::process);
    }
}
