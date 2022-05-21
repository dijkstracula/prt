import java.util.stream.Stream;

public class EventExperiment {

    static class AddEvent extends Event {
        final int amountToAdd;
        public AddEvent(int _amountToAdd) { amountToAdd = _amountToAdd; }

        public String toString() {
            return String.format("%s(%d)", getClass().getName(), amountToAdd);
        }
    }

    static class MulEvent extends Event {
        final int amountToMul;
        public MulEvent(int _amountToMul) { amountToMul = _amountToMul; }

        public String toString() {
            return String.format("%s(%d)", getClass().getName(), amountToMul);
        }
    }

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

        Stream<Event> str = Stream.of(add1, add1, dbl, add1);
        str.forEach(pm::process);
    }
}
