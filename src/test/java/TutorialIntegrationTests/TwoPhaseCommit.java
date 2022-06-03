package TutorialIntegrationTests;
import prt.*;

import java.text.MessageFormat;
import java.util.*;

/***************************************************************************
 * This file was auto-generated on Friday, 03 June 2022 at 12:05:33.
 * Please do not edit manually!
 **************************************************************************/

enum tTransStatus {
    SUCCESS(0),
    ERROR(1),
    TIMEOUT(2);

    private int val;
    tTransStatus(int i) { val = i; }
    public int getVal() { return val; }
}

public class TwoPhaseCommit {
    record DefaultEvent() implements PObserveEvent.PEvent { }
    record PHalt() implements PObserveEvent.PEvent { }
    record eWriteTransReq(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record eWriteTransResp(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record eReadTransReq(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record eReadTransResp(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record ePrepareReq(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record ePrepareResp(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record eCommitTrans(int payload) implements PObserveEvent.PEvent { }
    record eAbortTrans(int payload) implements PObserveEvent.PEvent { }
    record eInformCoordinator(long payload) implements PObserveEvent.PEvent { }
    record eMonitor_AtomicityInitialize(int payload) implements PObserveEvent.PEvent { }
    record eStartTimer() implements PObserveEvent.PEvent { }
    record eCancelTimer() implements PObserveEvent.PEvent { }
    record eTimeOut() implements PObserveEvent.PEvent { }
    record eDelayedTimeOut() implements PObserveEvent.PEvent { }
    record eDelayNodeFailure() implements PObserveEvent.PEvent { }
    record eShutDown(long payload) implements PObserveEvent.PEvent { }

    // PMachine Coordinator elided
    // PMachine Participant elided
    static class AtomicityInvariant extends Monitor {
        private HashMap<Integer,HashMap<Integer,Integer>> participantsResponse = new HashMap<Integer,HashMap<Integer,Integer>>();private int numParticipants = 0;
        private String INIT_STATE = "Init";
        private String WAITFOREVENTS_STATE = "WaitForEvents";

        private void Anon(eMonitor_AtomicityInitialize pEvent) {
            int n = pEvent.payload;

            numParticipants = n;
        }
        private void Anon_1(ePrepareResp pEvent) {
            HashMap<String, Object> resp = pEvent.payload;
            int transId = 0;
            int TMP_tmp0 = 0;
            int TMP_tmp1 = 0;
            boolean TMP_tmp2 = false;
            boolean TMP_tmp3 = false;
            HashMap<Integer,Integer> TMP_tmp4 = new HashMap<Integer,Integer>();
            int TMP_tmp5 = 0;
            HashMap<Integer,Integer> TMP_tmp6 = new HashMap<Integer,Integer>();
            int TMP_tmp7 = 0;
            int TMP_tmp8 = 0;
            int TMP_tmp9 = 0;

            TMP_tmp0 = ((Integer)resp.get("transId"));
            TMP_tmp1 = TMP_tmp0;
            transId = TMP_tmp1;
            TMP_tmp2 = participantsResponse.containsKey(transId);
            TMP_tmp3 = !(TMP_tmp2);
            if (TMP_tmp3) {
                TMP_tmp4 = new HashMap<Integer,Integer>();
                participantsResponse.put(transId,TMP_tmp4);
                ((HashMap<Integer,Integer>)participantsResponse.get(transId)).put(tTransStatus.SUCCESS.getVal(),0);
                ((HashMap<Integer,Integer>)participantsResponse.get(transId)).put(tTransStatus.ERROR.getVal(),0);
            }
            TMP_tmp5 = ((Integer)resp.get("status"));
            TMP_tmp6 = ((HashMap<Integer,Integer>)participantsResponse.get(transId));
            TMP_tmp7 = ((Integer)resp.get("status"));
            TMP_tmp8 = ((Integer)TMP_tmp6.get(TMP_tmp7));
            TMP_tmp9 = (TMP_tmp8 + 1);
            ((HashMap<Integer,Integer>)participantsResponse.get(transId)).put(TMP_tmp5,TMP_tmp9);
        }
        private void Anon_2(eWriteTransResp pEvent) {
            HashMap<String, Object> resp_1 = pEvent.payload;
            int TMP_tmp0_1 = 0;
            boolean TMP_tmp1_1 = false;
            int TMP_tmp2_1 = 0;
            boolean TMP_tmp3_1 = false;
            boolean TMP_tmp4_1 = false;
            String TMP_tmp5_1 = "";
            int TMP_tmp6_1 = 0;
            boolean TMP_tmp7_1 = false;
            int TMP_tmp8_1 = 0;
            HashMap<Integer,Integer> TMP_tmp9_1 = new HashMap<Integer,Integer>();
            int TMP_tmp10 = 0;
            boolean TMP_tmp11 = false;
            String TMP_tmp12 = "";
            int TMP_tmp13 = 0;
            HashMap<Integer,Integer> TMP_tmp14 = new HashMap<Integer,Integer>();
            int TMP_tmp15 = 0;
            int TMP_tmp16 = 0;
            HashMap<Integer,Integer> TMP_tmp17 = new HashMap<Integer,Integer>();
            int TMP_tmp18 = 0;
            String TMP_tmp19 = "";
            String TMP_tmp20 = "";
            int TMP_tmp21 = 0;
            boolean TMP_tmp22 = false;
            int TMP_tmp23 = 0;
            HashMap<Integer,Integer> TMP_tmp24 = new HashMap<Integer,Integer>();
            int TMP_tmp25 = 0;
            boolean TMP_tmp26 = false;
            int TMP_tmp27 = 0;
            String TMP_tmp28 = "";
            int TMP_tmp29 = 0;
            HashMap<Integer,Integer> TMP_tmp30 = new HashMap<Integer,Integer>();
            int TMP_tmp31 = 0;
            int TMP_tmp32 = 0;
            HashMap<Integer,Integer> TMP_tmp33 = new HashMap<Integer,Integer>();
            int TMP_tmp34 = 0;
            String TMP_tmp35 = "";
            String TMP_tmp36 = "";
            int TMP_tmp37 = 0;

            TMP_tmp0_1 = ((Integer)resp_1.get("transId"));
            TMP_tmp1_1 = participantsResponse.containsKey(TMP_tmp0_1);
            TMP_tmp4_1 = TMP_tmp1_1;
            if (TMP_tmp4_1) {
            }
            else
            {
                TMP_tmp2_1 = ((Integer)resp_1.get("status"));
                TMP_tmp3_1 = (TMP_tmp2_1 == tTransStatus.TIMEOUT.getVal());
                TMP_tmp4_1 = TMP_tmp3_1;
            }
            TMP_tmp5_1 = "Write transaction was responded to the client without receiving any responses from the participants!";
            tryAssert(TMP_tmp4_1, TMP_tmp5_1);
            TMP_tmp6_1 = ((Integer)resp_1.get("status"));
            TMP_tmp7_1 = (TMP_tmp6_1 == tTransStatus.SUCCESS.getVal());
            if (TMP_tmp7_1) {
                TMP_tmp8_1 = ((Integer)resp_1.get("transId"));
                TMP_tmp9_1 = ((HashMap<Integer,Integer>)participantsResponse.get(TMP_tmp8_1));
                TMP_tmp10 = ((Integer)TMP_tmp9_1.get(tTransStatus.SUCCESS.getVal()));
                TMP_tmp11 = (TMP_tmp10 == numParticipants);
                TMP_tmp12 = "Write transaction was responded as committed before receiving success from all participants. ";
                TMP_tmp13 = ((Integer)resp_1.get("transId"));
                TMP_tmp14 = ((HashMap<Integer,Integer>)participantsResponse.get(TMP_tmp13));
                TMP_tmp15 = ((Integer)TMP_tmp14.get(tTransStatus.SUCCESS.getVal()));
                TMP_tmp16 = ((Integer)resp_1.get("transId"));
                TMP_tmp17 = ((HashMap<Integer,Integer>)participantsResponse.get(TMP_tmp16));
                TMP_tmp18 = ((Integer)TMP_tmp17.get(tTransStatus.ERROR.getVal()));
                TMP_tmp19 = MessageFormat.format("participants sent success: {0}, participants sent error: {1}", TMP_tmp15, TMP_tmp18);
                TMP_tmp20 = (TMP_tmp12 + TMP_tmp19);
                tryAssert(TMP_tmp11, TMP_tmp20);
            }
            else
            {
                TMP_tmp21 = ((Integer)resp_1.get("status"));
                TMP_tmp22 = (TMP_tmp21 == tTransStatus.ERROR.getVal());
                if (TMP_tmp22) {
                    TMP_tmp23 = ((Integer)resp_1.get("transId"));
                    TMP_tmp24 = ((HashMap<Integer,Integer>)participantsResponse.get(TMP_tmp23));
                    TMP_tmp25 = ((Integer)TMP_tmp24.get(tTransStatus.ERROR.getVal()));
                    TMP_tmp26 = (TMP_tmp25 > 0);
                    TMP_tmp27 = ((Integer)resp_1.get("transId"));
                    TMP_tmp28 = MessageFormat.format("Write transaction {0} was responded as failed before receiving error from atleast one participant.", TMP_tmp27);
                    TMP_tmp29 = ((Integer)resp_1.get("transId"));
                    TMP_tmp30 = ((HashMap<Integer,Integer>)participantsResponse.get(TMP_tmp29));
                    TMP_tmp31 = ((Integer)TMP_tmp30.get(tTransStatus.SUCCESS.getVal()));
                    TMP_tmp32 = ((Integer)resp_1.get("transId"));
                    TMP_tmp33 = ((HashMap<Integer,Integer>)participantsResponse.get(TMP_tmp32));
                    TMP_tmp34 = ((Integer)TMP_tmp33.get(tTransStatus.ERROR.getVal()));
                    TMP_tmp35 = MessageFormat.format("participants sent success: {0}, participants sent error: {1}", TMP_tmp31, TMP_tmp34);
                    TMP_tmp36 = (TMP_tmp28 + TMP_tmp35);
                    tryAssert(TMP_tmp26, TMP_tmp36);
                }
            }
            TMP_tmp37 = ((Integer)resp_1.get("transId"));
            participantsResponse.remove(TMP_tmp37);
        }

        public AtomicityInvariant() {
            super();
            addState(new State.Builder(INIT_STATE)
                    .isInitialState(true)
                    .withEvent(eMonitor_AtomicityInitialize.class, e -> { Anon(e); gotoState(WAITFOREVENTS_STATE); })
                    .build());
            addState(new State.Builder(WAITFOREVENTS_STATE)
                    .isInitialState(false)
                    .withEvent(ePrepareResp.class, this::Anon_1)
                    .withEvent(eWriteTransResp.class, this::Anon_2)
                    .build());
        } // constructor
    } // AtomicityInvariant monitor definition
    static class Progress extends Monitor {
        private int pendingTransactions = 0;
        private String INIT_STATE = "Init";
        private String WAITFORRESPONSES_STATE = "WaitForResponses";
        private String ALLTRANSACTIONSFINISHED_STATE = "AllTransactionsFinished";

        private void Anon_3(eWriteTransReq pEvent) {
            int TMP_tmp0_2 = 0;

            TMP_tmp0_2 = (pendingTransactions + 1);
            pendingTransactions = TMP_tmp0_2;
        }
        private void Anon_4(eWriteTransResp pEvent) throws TransitionException {
            int TMP_tmp0_3 = 0;
            boolean TMP_tmp1_2 = false;

            TMP_tmp0_3 = (pendingTransactions - 1);
            pendingTransactions = TMP_tmp0_3;
            TMP_tmp1_2 = (pendingTransactions == 0);
            if (TMP_tmp1_2) {
                gotoState(ALLTRANSACTIONSFINISHED_STATE);
                return;
            }
        }
        private void Anon_5(eWriteTransReq pEvent) {
            int TMP_tmp0_4 = 0;

            TMP_tmp0_4 = (pendingTransactions + 1);
            pendingTransactions = TMP_tmp0_4;
        }
        private void Anon_6(eWriteTransReq pEvent) {
            int TMP_tmp0_5 = 0;

            TMP_tmp0_5 = (pendingTransactions + 1);
            pendingTransactions = TMP_tmp0_5;
        }

        public Progress() {
            super();
            addState(new State.Builder(INIT_STATE)
                    .isInitialState(true)
                    .withEvent(eWriteTransReq.class, e -> { Anon_3(e); gotoState(WAITFORRESPONSES_STATE); })
                    .build());
            addState(new State.Builder(WAITFORRESPONSES_STATE)
                    .isInitialState(false)
                    .withEvent(eWriteTransResp.class, this::Anon_4)
                    .withEvent(eWriteTransReq.class, this::Anon_5)
                    .build());
            addState(new State.Builder(ALLTRANSACTIONSFINISHED_STATE)
                    .isInitialState(false)
                    .withEvent(eWriteTransReq.class, e -> { Anon_6(e); gotoState(WAITFORRESPONSES_STATE); })
                    .build());
        } // constructor
    } // Progress monitor definition
    // PMachine SingleClientNoFailure elided
    // PMachine MultipleClientsNoFailure elided
    // PMachine MultipleClientsWithFailure elided
    // PMachine Client elided
    // PMachine Timer elided
    // PMachine FailureInjector elided
} // TwoPhaseCommit.java class definition
