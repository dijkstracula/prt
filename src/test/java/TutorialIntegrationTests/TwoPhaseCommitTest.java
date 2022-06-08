package TutorialIntegrationTests;

import java.util.List;

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
                m.process(new ePrepareResp(
                        new Gen_PTuple_5(participant, 0, tTransStatus.SUCCESS)));
            }

            m.process(new eWriteTransResp(new Gen_PTuple_2(0, tTransStatus.SUCCESS)));
        }

        @Test
        @DisplayName("Can handle successful rollbacks")
        public void successfulRollback() {
            TwoPhaseCommit.AtomicityInvariant m = initedMonitor();

            // Participants 1 and two say yes...
            for (long participant : List.of(1L,2L)) {
                m.process(new ePrepareResp(new Gen_PTuple_5(participant, 0, tTransStatus.SUCCESS)));
            }
            // Participant 3 says no!
            m.process(new ePrepareResp(new Gen_PTuple_5(3L, 0, tTransStatus.ERROR)));

            // should be able to handle a txn response with an error
            m.process(new eWriteTransResp(new Gen_PTuple_2(0, tTransStatus.ERROR)));
        }

        @Test
        @DisplayName("Throws on an unsuccessful commit (owing to a premature commit message)")
        public void prematureCommitTest() {
            TwoPhaseCommit.AtomicityInvariant m = initedMonitor();

            /* Only two SUCCESSes; one never arrives! */
            for (long participant : List.of(1L,2L)) {
                m.process(new ePrepareResp(new Gen_PTuple_5(participant, 0, tTransStatus.SUCCESS)));
            }

            assertThrows(PAssertionFailureException.class,
                    () -> m.process(new eWriteTransResp(new Gen_PTuple_2(0, tTransStatus.SUCCESS))));
        }

        @Test
        @DisplayName("Throws on an unsuccessful commit (owing to a premature commit message)")
        public void unsuccessfulCommitTest() {
            TwoPhaseCommit.AtomicityInvariant m = initedMonitor();

            for (long participant : List.of(1L,2L)) {
                m.process(new ePrepareResp(new Gen_PTuple_5(participant, 0, tTransStatus.SUCCESS)));
            }
            // Participant 3 says no dice!
            m.process(new ePrepareResp(new Gen_PTuple_5(3L, 0, tTransStatus.ERROR)));

            assertThrows(PAssertionFailureException.class,
                    () -> m.process(new eWriteTransResp(new Gen_PTuple_2(0, tTransStatus.SUCCESS))));
        }

    }

    public static class ProgressSpecTest {
        // TODO: we don't handle hot/cold states, which seems important for testing this spec.
        // Leave it for the time being.
    }
}
