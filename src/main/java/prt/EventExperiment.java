package prt;

import java.util.Random;
import java.util.stream.Stream;

public class EventExperiment {
    record AddEvent(int amountToAdd) implements PObserveEvent.PEvent { }
    record MulEvent(int amountToMul) implements PObserveEvent.PEvent { }

    static class ParityMonitor extends Monitor {

        private String EVEN_STATE = "even";
        private String ODD_STATE = "odd";

        public ParityMonitor() {
            super();

            addState(new State.Builder(EVEN_STATE)
                    .withEntry(() -> System.out.println("Entering EVEN"))
                    .withEvent(AddEvent.class,
                            ae -> { if (ae.amountToAdd % 2 == 1) gotoState(ODD_STATE); })
                    .withExit(() -> System.out.println("Leaving EVEN"))
                    .build());

            addState(new State.Builder(ODD_STATE)
                    .isInitialState(true)
                    .withEntry(() -> System.out.println("Entering ODD"))
                    .withEvent(AddEvent.class,
                            ae -> { if (ae.amountToAdd % 2 == 1) gotoState(EVEN_STATE); })
                    .withEvent(MulEvent.class,
                            me -> { if (me.amountToMul % 2 == 0) gotoState(EVEN_STATE); })
                    .withExit(() -> System.out.println("Leaving ODD"))
                    .build());
        }
    }

    public static void main(String[] args) {
        ParityMonitor pm = new ParityMonitor();
        pm.ready();

        Random r = new Random();

        Stream<PObserveEvent.PEvent> payloads = Stream.generate(() -> {
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
