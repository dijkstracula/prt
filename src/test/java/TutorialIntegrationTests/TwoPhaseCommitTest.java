package TutorialIntegrationTests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prt.PAssertionFailureException;

import static org.junit.jupiter.api.Assertions.*;

import static TutorialIntegrationTests.TwoPhaseCommit.*;

public class TwoPhaseCommitTest {
    public static class AtomnicityInvariantSpecTest {
        private TwoPhaseCommit.AtomicityInvariant initedMonitor() {
            TwoPhaseCommit.AtomicityInvariant m = new TwoPhaseCommit.AtomicityInvariant();
            m.ready();
            m.process(new eMonitor_AtomicityInitialize(3)); // We'll now be in WaitForEvents
            return m;
        }

        @Test
        @DisplayName("Can handle successful commits")
        public void successfulCommitTest() {
            TwoPhaseCommit.AtomicityInvariant m = initedMonitor();

            for (long participant : List.of(1L,2L,3L)) {
                HashMap<String, Object> prepareResp = new HashMap<>(
                        Map.of("participant", participant,
                                "transId", 0,
                                "status", tTransStatus.SUCCESS.getVal()));
                m.process(new ePrepareResp(prepareResp));
            }

            HashMap<String, Object> transResp = new HashMap<>(
                    Map.of("transId", 0,
                            "status", tTransStatus.SUCCESS.getVal()));
            m.process(new eWriteTransResp(transResp));
        }

        @Test
        @DisplayName("Can handle successful commits")
        public void successfulRollback() {
            TwoPhaseCommit.AtomicityInvariant m = initedMonitor();

            // Participants 1 and two say yes...
            for (long participant : List.of(1L,2L)) {
                HashMap<String, Object> prepareResp = new HashMap<>(
                        Map.of("participant", participant,
                                "transId", 0,
                                "status", tTransStatus.SUCCESS.getVal()));
                m.process(new ePrepareResp(prepareResp));
            }
            // Participant 3 says no!
            HashMap<String, Object> prepareResp = new HashMap<>(
                    Map.of("participant", 3L,
                            "transId", 0,
                            "status", tTransStatus.ERROR.getVal()));
            m.process(new ePrepareResp(prepareResp));

            HashMap<String, Object> transResp = new HashMap<>(
                    Map.of("transId", 0,
                            "status", tTransStatus.ERROR.getVal()));
            m.process(new eWriteTransResp(transResp));
        }

        @Test
        @DisplayName("Throws on an unsuccessful commit (owing to a premature commit message)")
        public void prematureCommitTest() {
            TwoPhaseCommit.AtomicityInvariant m = initedMonitor();

            /* Only two SUCCESSes; one never arrives! */
            for (long participant : List.of(1L,2L)) {
                HashMap<String, Object> prepareResp = new HashMap<>(
                        Map.of("participant", participant,
                                "transId", 0,
                                "status", tTransStatus.SUCCESS.getVal()));
                m.process(new ePrepareResp(prepareResp));
            }

            HashMap<String, Object> transResp = new HashMap<>(
                    Map.of("transId", 0,
                            "status", tTransStatus.SUCCESS.getVal()));
            assertThrows(PAssertionFailureException.class, () -> m.process(new eWriteTransResp(transResp)));
        }

        @Test
        @DisplayName("Throws on an unsuccessful commit (owing to a premature commit message)")
        public void unsuccessfulCommitTest() {
            TwoPhaseCommit.AtomicityInvariant m = initedMonitor();

            for (long participant : List.of(1L,2L)) {
                HashMap<String, Object> prepareResp = new HashMap<>(
                        Map.of("participant", participant,
                                "transId", 0,
                                "status", tTransStatus.SUCCESS.getVal()));
                m.process(new ePrepareResp(prepareResp));
            }
            // Participant 3 says no dice!
            HashMap<String, Object> prepareResp = new HashMap<>(
                    Map.of("participant", 3L,
                            "transId", 0,
                            "status", tTransStatus.ERROR.getVal()));
            m.process(new ePrepareResp(prepareResp));

            HashMap<String, Object> transResp = new HashMap<>(
                    Map.of("transId", 0,
                            "status", tTransStatus.SUCCESS.getVal()));
            assertThrows(PAssertionFailureException.class, () -> m.process(new eWriteTransResp(transResp)));
        }

    }

    public static class ProgressSpecTest {
        // TODO: we don't handle hot/cold states, which seems important for testing this spec.
        // Leave it for the time being.
    }
}
