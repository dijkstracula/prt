package TutorialIntegrationTests;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

            HashMap<String, Object> withdrawPayload = new HashMap<>(
                    Map.of("accountId", 31337, /* Uh oh! This should be 100 or 101. */
                           "amount", 10,
                            "rId", 0));

            assertThrows(prt.PAssertionFailureException.class,
                    () -> m.process(new eWithDrawReq(withdrawPayload)),
                    "Assertion failure: Unknown accountId 102 in the withdraw request. Valid accountIds = [100, 101]");
        }


        @Test
        @DisplayName("Can consume some valid withdraw events")
        void processValidWithdraws() {
            BankBalanceIsAlwaysCorrect m = initedMonitor();

            HashMap<String, Object> withdrawReqPayload = new HashMap<>(
                    Map.of("Client", 1L,
                            "accountId", 100,
                            "amount", 10,
                            "rId", 0));
            m.process(new eWithDrawReq(withdrawReqPayload));

            HashMap<String, Object> withdrawRespPayload = new HashMap<>(
                    Map.of("status", tWithDrawRespStatus.WITHDRAW_SUCCESS.getVal(),
                            "accountId", 100,
                            "balance", 32,
                            "rId", 0));
            m.process(new eWithDrawResp(withdrawRespPayload));
        }

        @Test
        @DisplayName("Throws on invalid withdraw events")
        void ThrowsOnInvalidWithdraws() {
            BankBalanceIsAlwaysCorrect m = initedMonitor();

            HashMap<String, Object> withdrawReqPayload = new HashMap<>(
                    Map.of("Client", 1L,
                            "accountId", 100,
                            "amount", 10,
                            "rId", 0));
            m.process(new eWithDrawReq(withdrawReqPayload));

            HashMap<String, Object> withdrawRespPayload = new HashMap<>(
                    Map.of("status", tWithDrawRespStatus.WITHDRAW_SUCCESS.getVal(),
                            "accountId", 100,
                            "balance", 1000, /* Uh oh! This should be 42 - 10 = 32. */
                            "rId", 0));
            assertThrows(prt.PAssertionFailureException.class,
                    () -> m.process(new eWithDrawResp(withdrawRespPayload)));
        }
    }


}
