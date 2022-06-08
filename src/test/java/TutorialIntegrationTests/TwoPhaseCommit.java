package TutorialIntegrationTests;
import prt.*;

import java.text.MessageFormat;
import java.util.*;


/***************************************************************************
 * This file was auto-generated on Wednesday, 08 June 2022 at 11:16:40.
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
    static class Gen_PTuple {
        // (key:string,val:int,transId:int)
        public String key;
        public int val;
        public int transId;

        public Gen_PTuple() {
            this.key = "";
            this.val = 0;
            this.transId = 0;
        }

        public Gen_PTuple(String key, int val, int transId) {
            this.key = key;
            this.val = val;
            this.transId = transId;
        }

        public Gen_PTuple clone() {
            return new Gen_PTuple(key, val, transId);
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple");
            sb.append("[");
            sb.append("key=");
            sb.append(key);
            sb.append(",");
            sb.append("val=");
            sb.append(val);
            sb.append(",");
            sb.append("transId=");
            sb.append(transId);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple class definition

    static class Gen_PTuple_1 {
        // (client:Client,trans:(key:string,val:int,transId:int))
        public long client;
        public Gen_PTuple trans;

        public Gen_PTuple_1() {
            this.client = 0L;
            this.trans = new Gen_PTuple();
        }

        public Gen_PTuple_1(long client, Gen_PTuple trans) {
            this.client = client;
            this.trans = trans;
        }

        public Gen_PTuple_1 clone() {
            return new Gen_PTuple_1(client, trans.clone());
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple_1");
            sb.append("[");
            sb.append("client=");
            sb.append(client);
            sb.append(",");
            sb.append("trans=");
            sb.append(trans);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple_1 class definition

    static class Gen_PTuple_2 {
        // (transId:int,status:tTransStatus)
        public int transId;
        public int status;

        public Gen_PTuple_2() {
            this.transId = 0;
            this.status = 0;
        }

        public Gen_PTuple_2(int transId, int status) {
            this.transId = transId;
            this.status = status;
        }

        public Gen_PTuple_2 clone() {
            return new Gen_PTuple_2(transId, status);
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple_2");
            sb.append("[");
            sb.append("transId=");
            sb.append(transId);
            sb.append(",");
            sb.append("status=");
            sb.append(status);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple_2 class definition

    static class Gen_PTuple_3 {
        // (client:Client,key:string)
        public long client;
        public String key;

        public Gen_PTuple_3() {
            this.client = 0L;
            this.key = "";
        }

        public Gen_PTuple_3(long client, String key) {
            this.client = client;
            this.key = key;
        }

        public Gen_PTuple_3 clone() {
            return new Gen_PTuple_3(client, key);
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple_3");
            sb.append("[");
            sb.append("client=");
            sb.append(client);
            sb.append(",");
            sb.append("key=");
            sb.append(key);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple_3 class definition

    static class Gen_PTuple_4 {
        // (key:string,val:int,status:tTransStatus)
        public String key;
        public int val;
        public int status;

        public Gen_PTuple_4() {
            this.key = "";
            this.val = 0;
            this.status = 0;
        }

        public Gen_PTuple_4(String key, int val, int status) {
            this.key = key;
            this.val = val;
            this.status = status;
        }

        public Gen_PTuple_4 clone() {
            return new Gen_PTuple_4(key, val, status);
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple_4");
            sb.append("[");
            sb.append("key=");
            sb.append(key);
            sb.append(",");
            sb.append("val=");
            sb.append(val);
            sb.append(",");
            sb.append("status=");
            sb.append(status);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple_4 class definition

    static class Gen_PTuple_5 {
        // (participant:Participant,transId:int,status:tTransStatus)
        public long participant;
        public int transId;
        public int status;

        public Gen_PTuple_5() {
            this.participant = 0L;
            this.transId = 0;
            this.status = 0;
        }

        public Gen_PTuple_5(long participant, int transId, int status) {
            this.participant = participant;
            this.transId = transId;
            this.status = status;
        }

        public Gen_PTuple_5 clone() {
            return new Gen_PTuple_5(participant, transId, status);
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple_5");
            sb.append("[");
            sb.append("participant=");
            sb.append(participant);
            sb.append(",");
            sb.append("transId=");
            sb.append(transId);
            sb.append(",");
            sb.append("status=");
            sb.append(status);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple_5 class definition

    static class Gen_PTuple_6 {
        // (numClients:int,numParticipants:int,numTransPerClient:int,failParticipants:int)
        public int numClients;
        public int numParticipants;
        public int numTransPerClient;
        public int failParticipants;

        public Gen_PTuple_6() {
            this.numClients = 0;
            this.numParticipants = 0;
            this.numTransPerClient = 0;
            this.failParticipants = 0;
        }

        public Gen_PTuple_6(int numClients, int numParticipants, int numTransPerClient, int failParticipants) {
            this.numClients = numClients;
            this.numParticipants = numParticipants;
            this.numTransPerClient = numTransPerClient;
            this.failParticipants = failParticipants;
        }

        public Gen_PTuple_6 clone() {
            return new Gen_PTuple_6(numClients, numParticipants, numTransPerClient, failParticipants);
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple_6");
            sb.append("[");
            sb.append("numClients=");
            sb.append(numClients);
            sb.append(",");
            sb.append("numParticipants=");
            sb.append(numParticipants);
            sb.append(",");
            sb.append("numTransPerClient=");
            sb.append(numTransPerClient);
            sb.append(",");
            sb.append("failParticipants=");
            sb.append(failParticipants);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple_6 class definition

    static class Gen_PTuple_7 {
        // (nodes:set[machine],nFailures:int)
        public LinkedHashSet<Long> nodes;
        public int nFailures;

        public Gen_PTuple_7() {
            this.nodes = new LinkedHashSet<Long>();
            this.nFailures = 0;
        }

        public Gen_PTuple_7(LinkedHashSet<Long> nodes, int nFailures) {
            this.nodes = nodes;
            this.nFailures = nFailures;
        }

        public Gen_PTuple_7 clone() {
            return new Gen_PTuple_7((LinkedHashSet<Long>)Values.clone(nodes), nFailures);
        } // clone() method end
        public String toString() {
            StringBuilder sb = new StringBuilder("Gen_PTuple_7");
            sb.append("[");
            sb.append("nodes=");
            sb.append(nodes);
            sb.append(",");
            sb.append("nFailures=");
            sb.append(nFailures);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //Gen_PTuple_7 class definition


    record DefaultEvent() implements PObserveEvent.PEvent { }
    record PHalt() implements PObserveEvent.PEvent { }
    record eWriteTransReq(Gen_PTuple_1 payload) implements PObserveEvent.PEvent { }
    record eWriteTransResp(Gen_PTuple_2 payload) implements PObserveEvent.PEvent { }
    record eReadTransReq(Gen_PTuple_3 payload) implements PObserveEvent.PEvent { }
    record eReadTransResp(Gen_PTuple_4 payload) implements PObserveEvent.PEvent { }
    record ePrepareReq(Gen_PTuple payload) implements PObserveEvent.PEvent { }
    record ePrepareResp(Gen_PTuple_5 payload) implements PObserveEvent.PEvent { }
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
        private HashMap<Integer,HashMap<Integer,Integer>> participantsResponse = new HashMap<Integer,HashMap<Integer,Integer>>();
        public HashMap<Integer,HashMap<Integer,Integer>> getParticipantsResponse() { return this.participantsResponse; };

        private int numParticipants = 0;
        public int getNumParticipants() { return this.numParticipants; };


        public String INIT_STATE = "Init";
        public String WAITFOREVENTS_STATE = "WaitForEvents";

        private void Anon(eMonitor_AtomicityInitialize pEvent) {
            int n = pEvent.payload;

            numParticipants = n;
        }
        private void Anon_1(ePrepareResp pEvent) {
            Gen_PTuple_5 resp = pEvent.payload;
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

            TMP_tmp0 = resp.transId;
            TMP_tmp1 = TMP_tmp0;
            transId = TMP_tmp1;
            TMP_tmp2 = participantsResponse.containsKey(transId);
            TMP_tmp3 = !(TMP_tmp2);
            if (TMP_tmp3) {
                TMP_tmp4 = new HashMap<Integer,Integer>();
                participantsResponse.put(transId,TMP_tmp4);
                participantsResponse.get(transId).put(tTransStatus.SUCCESS.getVal(),0);
                participantsResponse.get(transId).put(tTransStatus.ERROR.getVal(),0);
            }
            TMP_tmp5 = resp.status;
            TMP_tmp6 = participantsResponse.get(transId);
            TMP_tmp7 = resp.status;
            TMP_tmp8 = TMP_tmp6.get(TMP_tmp7);
            TMP_tmp9 = (TMP_tmp8 + 1);
            participantsResponse.get(transId).put(TMP_tmp5,TMP_tmp9);
        }
        private void Anon_2(eWriteTransResp pEvent) {
            Gen_PTuple_2 resp_1 = pEvent.payload;
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

            TMP_tmp0_1 = resp_1.transId;
            TMP_tmp1_1 = participantsResponse.containsKey(TMP_tmp0_1);
            TMP_tmp4_1 = TMP_tmp1_1;
            if (TMP_tmp4_1) {} else
            {
                TMP_tmp2_1 = resp_1.status;
                TMP_tmp3_1 = (TMP_tmp2_1 == tTransStatus.TIMEOUT.getVal());
                TMP_tmp4_1 = TMP_tmp3_1;
            }
            TMP_tmp5_1 = "Write transaction was responded to the client without receiving any responses from the participants!";
            tryAssert(TMP_tmp4_1, TMP_tmp5_1);
            TMP_tmp6_1 = resp_1.status;
            TMP_tmp7_1 = (TMP_tmp6_1 == tTransStatus.SUCCESS.getVal());
            if (TMP_tmp7_1) {
                TMP_tmp8_1 = resp_1.transId;
                TMP_tmp9_1 = participantsResponse.get(TMP_tmp8_1);
                TMP_tmp10 = TMP_tmp9_1.get(tTransStatus.SUCCESS.getVal());
                TMP_tmp11 = (TMP_tmp10 == numParticipants);
                TMP_tmp12 = "Write transaction was responded as committed before receiving success from all participants. ";
                TMP_tmp13 = resp_1.transId;
                TMP_tmp14 = participantsResponse.get(TMP_tmp13);
                TMP_tmp15 = TMP_tmp14.get(tTransStatus.SUCCESS.getVal());
                TMP_tmp16 = resp_1.transId;
                TMP_tmp17 = participantsResponse.get(TMP_tmp16);
                TMP_tmp18 = TMP_tmp17.get(tTransStatus.ERROR.getVal());
                TMP_tmp19 = MessageFormat.format("participants sent success: {0}, participants sent error: {1}", TMP_tmp15, TMP_tmp18);
                TMP_tmp20 = (TMP_tmp12 + TMP_tmp19);
                tryAssert(TMP_tmp11, TMP_tmp20);
            }
            else
            {
                TMP_tmp21 = resp_1.status;
                TMP_tmp22 = (TMP_tmp21 == tTransStatus.ERROR.getVal());
                if (TMP_tmp22) {
                    TMP_tmp23 = resp_1.transId;
                    TMP_tmp24 = participantsResponse.get(TMP_tmp23);
                    TMP_tmp25 = TMP_tmp24.get(tTransStatus.ERROR.getVal());
                    TMP_tmp26 = (TMP_tmp25 > 0);
                    TMP_tmp27 = resp_1.transId;
                    TMP_tmp28 = MessageFormat.format("Write transaction {0} was responded as failed before receiving error from atleast one participant.", TMP_tmp27);
                    TMP_tmp29 = resp_1.transId;
                    TMP_tmp30 = participantsResponse.get(TMP_tmp29);
                    TMP_tmp31 = TMP_tmp30.get(tTransStatus.SUCCESS.getVal());
                    TMP_tmp32 = resp_1.transId;
                    TMP_tmp33 = participantsResponse.get(TMP_tmp32);
                    TMP_tmp34 = TMP_tmp33.get(tTransStatus.ERROR.getVal());
                    TMP_tmp35 = MessageFormat.format("participants sent success: {0}, participants sent error: {1}", TMP_tmp31, TMP_tmp34);
                    TMP_tmp36 = (TMP_tmp28 + TMP_tmp35);
                    tryAssert(TMP_tmp26, TMP_tmp36);
                }
            }
            TMP_tmp37 = resp_1.transId;
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
        public int getPendingTransactions() { return this.pendingTransactions; };


        public String INIT_STATE = "Init";
        public String WAITFORRESPONSES_STATE = "WaitForResponses";
        public String ALLTRANSACTIONSFINISHED_STATE = "AllTransactionsFinished";

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
