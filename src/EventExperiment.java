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
                    .withEntry(e -> System.out.println("Entering EVEN because of " + e.toString()))
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 1) { gotoState(ParityStates.ODD); throw new RuntimeException("Should be dead code!"); } })
                    .withExit(() -> System.out.println("Leaving EVEN"))
                    .build());

            addState(new State.Builder<>(ParityStates.ODD)
                    .isInitialState(true)
                    .withEntry(e -> System.out.println("Entering ODD because of " + e.toString()))
                    .withEvent(AddEvent.class,
                            ae -> {if (ae.amountToAdd % 2 == 1) { gotoState(ParityStates.EVEN); throw new RuntimeException("Should be dead code!"); } })
                    .withEvent(MulEvent.class,
                            me -> {if (me.amountToMul % 2 == 0) { gotoState(ParityStates.EVEN); throw new RuntimeException("Should be dead code!"); } })
                    .withExit(() -> System.out.println("Leaving ODD"))
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
        try {
            payloads.forEach(pm::process);
        } catch (UnhandledEventException e) {
            System.out.println("Uh oh! " + e);
        }
    }
}
