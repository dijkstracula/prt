package TutorialIntegrationTests;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prt.Monitor;
import prt.PAssertionFailureException;
import prt.UnhandledEventException;

import static org.junit.jupiter.api.Assertions.*;

import static TutorialIntegrationTests.ClientServer.*;

public class ClientServerTest {
    public static class TestBankBalanceIsAlwaysCorrect {

        HashMap<Integer, Integer> initialBalances =
                new HashMap<>(Map.of(100, 42, 101, 10));

        private BankBalanceIsAlwaysCorrect initedMonitor() {
            BankBalanceIsAlwaysCorrect m = new BankBalanceIsAlwaysCorrect();
            m.ready();
            m.process(new eSpec_BankBalanceIsAlwaysCorrect_Init(initialBalances));

            return m;
        }

        @Test
        @DisplayName("Can initialise a BankBalanceIsAlwaysCorrect monitor")
        void initMonitor() {
            BankBalanceIsAlwaysCorrect m = initedMonitor();
        }


        @Test
        @DisplayName("Asserts on invalid account ID")
        void assertsOnInvalidAccountID() {
            BankBalanceIsAlwaysCorrect m = initedMonitor();

            assertThrows(prt.PAssertionFailureException.class,
                    () -> m.process(new eWithDrawReq(
                            new Gen_PTuple_2(0L, 31337, 10, 0))),
                    "Assertion failure: Unknown accountId 102 in the withdraw request. Valid accountIds = [100, 101]");
        }


        @Test
        @DisplayName("Can consume some valid withdraw events")
        void processValidWithdraws() {
            BankBalanceIsAlwaysCorrect m = initedMonitor();

            m.process(new eWithDrawReq(
                    new Gen_PTuple_2(1L, 100, 10, 0)
            ));

            m.process(new eWithDrawResp(
                    new Gen_PTuple_3(tWithDrawRespStatus.WITHDRAW_SUCCESS, 100, 32, 0)));
        }

        @Test
        @DisplayName("Throws on invalid withdraw events")
        void ThrowsOnInvalidWithdraws() {
            BankBalanceIsAlwaysCorrect m = initedMonitor();

            m.process(new eWithDrawReq(new Gen_PTuple_2(1L, 100, 10, 0)));

            assertThrows(prt.PAssertionFailureException.class,
                    () -> m.process(new eWithDrawResp(new Gen_PTuple_3(
                            tWithDrawRespStatus.WITHDRAW_SUCCESS,
                            100,
                            1000, /* Uh oh! The balance should be 42 - 10 = 32. */
                            0))));
        }
    }

    public static class GuaranteedWithDrawProgressTest {
        private Monitor initedMonitor () {
            GuaranteedWithDrawProgress m = new GuaranteedWithDrawProgress();
            m.ready();

            return m;
        }

        @Test
        @DisplayName("Can process valid events")
        public void testWithDrawReqs() {
            Monitor m = initedMonitor();

            m.process(new eWithDrawReq(new Gen_PTuple_2(1L, 100, 10, 0)));

            m.process(new eWithDrawResp(new Gen_PTuple_3(
                tWithDrawRespStatus.WITHDRAW_SUCCESS,
                100,
                90,
                0)));
        }

        @Test
        @DisplayName("Throws un invalid state transitions")
        public void testInvalidWithDrawReqs() {
            Monitor m = initedMonitor();

            // We begin in the NopendingRequests state, but that state has no handler
            // for a withDrawRewp.
            assertThrows(UnhandledEventException.class, () -> m.process(new eWithDrawResp(
                    new Gen_PTuple_3(
                            tWithDrawRespStatus.WITHDRAW_ERROR,
                            100,
                            90,
                            0))));
        }

        @Test
        @DisplayName("Throws un invalid transactions")
        public void testInvalidWithDrawReqs2() {
            Monitor m = initedMonitor();

            m.process(new eWithDrawReq(new Gen_PTuple_2(1L, 100, 10, 0)));

            // We begin in the NopendingRequests state, but that state has no handler
            // for a withDrawRewp.
            assertThrows(PAssertionFailureException.class, () -> m.process(new eWithDrawResp(
                    new Gen_PTuple_3(
                            tWithDrawRespStatus.WITHDRAW_SUCCESS,
                            100,
                            90,
                            99999 /* We have never seen this rid before! */
                    ))));
        }
    }


}
