package tutorialmonitors;
import events.PObserveEvent;

import prt.*;

import java.text.MessageFormat;
import java.util.*;
/***************************************************************************
 * This file was auto-generated on Friday, 17 June 2022 at 14:23:46.
 * Please do not edit manually!
 **************************************************************************/

public class TwoPhaseCommit {
    /** Enums */
    public static class tTransStatus {
        public static final int SUCCESS = 0;
        public static final int ERROR = 1;
        public static final int TIMEOUT = 2;
    }

    /** Tuples */
    // (key:string,val:int,transId:int)
    public static class PTuple_key_val_transId implements Values.PTuple<PTuple_key_val_transId> {
        public String key;
        public int val;
        public int transId;

        public PTuple_key_val_transId() {
            this.key = "";
            this.val = 0;
            this.transId = 0;
        }

        public PTuple_key_val_transId(String key, int val, int transId) {
            this.key = key;
            this.val = val;
            this.transId = transId;
        }

        public PTuple_key_val_transId deepClone() {
            return new PTuple_key_val_transId(key, val, transId);
        } // deepClone()

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PTuple_key_val_transId that = (PTuple_key_val_transId) o;
            return val == that.val && transId == that.transId && Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, val, transId);
        }

        public boolean deepEquals(PTuple_key_val_transId other) {
            return (true
                    && Values.deepEquals(this.key, other.key)
                    && this.val == other.val
                    && this.transId == other.transId
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_key_val_transId");
            sb.append("[");
            sb.append("key=" + key);
            sb.append(",val=" + val);
            sb.append(",transId=" + transId);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_key_val_transId class definition

    // (client:Client,trans:(key:string,val:int,transId:int))
    public static class PTuple_client_trans implements Values.PTuple<PTuple_client_trans> {
        public long client;
        public PTuple_key_val_transId trans;

        public PTuple_client_trans() {
            this.client = 0L;
            this.trans = new PTuple_key_val_transId();
        }

        public PTuple_client_trans(long client, PTuple_key_val_transId trans) {
            this.client = client;
            this.trans = trans;
        }

        public PTuple_client_trans deepClone() {
            return new PTuple_client_trans(client, trans.deepClone());
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_client_trans)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_client_trans other) {
            return (true
                    && this.client == other.client
                    && Values.deepEquals(this.trans, other.trans)
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_client_trans");
            sb.append("[");
            sb.append("client=" + client);
            sb.append(",trans=" + trans);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_client_trans class definition

    // (transId:int,status:tTransStatus)
    public static class PTuple_transId_status implements Values.PTuple<PTuple_transId_status> {
        public int transId;
        public int status;

        public PTuple_transId_status() {
            this.transId = 0;
            this.status = 0;
        }

        public PTuple_transId_status(int transId, int status) {
            this.transId = transId;
            this.status = status;
        }

        public PTuple_transId_status deepClone() {
            return new PTuple_transId_status(transId, status);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_transId_status)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_transId_status other) {
            return (true
                    && this.transId == other.transId
                    && this.status == other.status
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_transId_status");
            sb.append("[");
            sb.append("transId=" + transId);
            sb.append(",status=" + status);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_transId_status class definition

    // (client:Client,key:string)
    public static class PTuple_client_key implements Values.PTuple<PTuple_client_key> {
        public long client;
        public String key;

        public PTuple_client_key() {
            this.client = 0L;
            this.key = "";
        }

        public PTuple_client_key(long client, String key) {
            this.client = client;
            this.key = key;
        }

        public PTuple_client_key deepClone() {
            return new PTuple_client_key(client, key);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_client_key)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_client_key other) {
            return (true
                    && this.client == other.client
                    && Values.deepEquals(this.key, other.key)
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_client_key");
            sb.append("[");
            sb.append("client=" + client);
            sb.append(",key=" + key);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_client_key class definition

    // (key:string,val:int,status:tTransStatus)
    public static class PTuple_key_val_status implements Values.PTuple<PTuple_key_val_status> {
        public String key;
        public int val;
        public int status;

        public PTuple_key_val_status() {
            this.key = "";
            this.val = 0;
            this.status = 0;
        }

        public PTuple_key_val_status(String key, int val, int status) {
            this.key = key;
            this.val = val;
            this.status = status;
        }

        public PTuple_key_val_status deepClone() {
            return new PTuple_key_val_status(key, val, status);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_key_val_status)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_key_val_status other) {
            return (true
                    && Values.deepEquals(this.key, other.key)
                    && this.val == other.val
                    && this.status == other.status
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_key_val_status");
            sb.append("[");
            sb.append("key=" + key);
            sb.append(",val=" + val);
            sb.append(",status=" + status);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_key_val_status class definition

    // (participant:Participant,transId:int,status:tTransStatus)
    public static class PTuple_participant_transId_status implements Values.PTuple<PTuple_participant_transId_status> {
        public long participant;
        public int transId;
        public int status;

        public PTuple_participant_transId_status() {
            this.participant = 0L;
            this.transId = 0;
            this.status = 0;
        }

        public PTuple_participant_transId_status(long participant, int transId, int status) {
            this.participant = participant;
            this.transId = transId;
            this.status = status;
        }

        public PTuple_participant_transId_status deepClone() {
            return new PTuple_participant_transId_status(participant, transId, status);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_participant_transId_status)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_participant_transId_status other) {
            return (true
                    && this.participant == other.participant
                    && this.transId == other.transId
                    && this.status == other.status
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_participant_transId_status");
            sb.append("[");
            sb.append("participant=" + participant);
            sb.append(",transId=" + transId);
            sb.append(",status=" + status);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_participant_transId_status class definition

    // (numClients:int,numParticipants:int,numTransPerClient:int,failParticipants:int)
    public static class PTuple_numClients_numParticipants_numTransPerClient_failParticipants implements Values.PTuple<PTuple_numClients_numParticipants_numTransPerClient_failParticipants> {
        public int numClients;
        public int numParticipants;
        public int numTransPerClient;
        public int failParticipants;

        public PTuple_numClients_numParticipants_numTransPerClient_failParticipants() {
            this.numClients = 0;
            this.numParticipants = 0;
            this.numTransPerClient = 0;
            this.failParticipants = 0;
        }

        public PTuple_numClients_numParticipants_numTransPerClient_failParticipants(int numClients, int numParticipants, int numTransPerClient, int failParticipants) {
            this.numClients = numClients;
            this.numParticipants = numParticipants;
            this.numTransPerClient = numTransPerClient;
            this.failParticipants = failParticipants;
        }

        public PTuple_numClients_numParticipants_numTransPerClient_failParticipants deepClone() {
            return new PTuple_numClients_numParticipants_numTransPerClient_failParticipants(numClients, numParticipants, numTransPerClient, failParticipants);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_numClients_numParticipants_numTransPerClient_failParticipants)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_numClients_numParticipants_numTransPerClient_failParticipants other) {
            return (true
                    && this.numClients == other.numClients
                    && this.numParticipants == other.numParticipants
                    && this.numTransPerClient == other.numTransPerClient
                    && this.failParticipants == other.failParticipants
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_numClients_numParticipants_numTransPerClient_failParticipants");
            sb.append("[");
            sb.append("numClients=" + numClients);
            sb.append(",numParticipants=" + numParticipants);
            sb.append(",numTransPerClient=" + numTransPerClient);
            sb.append(",failParticipants=" + failParticipants);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_numClients_numParticipants_numTransPerClient_failParticipants class definition

    // (nodes:set[machine],nFailures:int)
    public static class PTuple_nodes_nFailures implements Values.PTuple<PTuple_nodes_nFailures> {
        public LinkedHashSet<Long> nodes;
        public int nFailures;

        public PTuple_nodes_nFailures() {
            this.nodes = new LinkedHashSet<Long>();
            this.nFailures = 0;
        }

        public PTuple_nodes_nFailures(LinkedHashSet<Long> nodes, int nFailures) {
            this.nodes = nodes;
            this.nFailures = nFailures;
        }

        public PTuple_nodes_nFailures deepClone() {
            return new PTuple_nodes_nFailures((LinkedHashSet<Long>)Values.deepClone(nodes), nFailures);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_nodes_nFailures)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_nodes_nFailures other) {
            return (true
                    && Values.deepEquals(this.nodes, other.nodes)
                    && this.nFailures == other.nFailures
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_nodes_nFailures");
            sb.append("[");
            sb.append("nodes=" + nodes);
            sb.append(",nFailures=" + nFailures);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_nodes_nFailures class definition


    /** Events */
    public static class DefaultEvent implements PObserveEvent.PEvent<Void> {
        public DefaultEvent() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for DefaultEvent
    public static class PHalt implements PObserveEvent.PEvent<Void> {
        public PHalt() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for PHalt
    public static class eWriteTransReq implements PObserveEvent.PEvent<PTuple_client_trans> {
        public eWriteTransReq(PTuple_client_trans p) { this.payload = p; }
        private PTuple_client_trans payload;
        public PTuple_client_trans getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eWriteTransReq
    public static class eWriteTransResp implements PObserveEvent.PEvent<PTuple_transId_status> {
        public eWriteTransResp(PTuple_transId_status p) { this.payload = p; }
        private PTuple_transId_status payload;
        public PTuple_transId_status getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eWriteTransResp
    public static class eReadTransReq implements PObserveEvent.PEvent<PTuple_client_key> {
        public eReadTransReq(PTuple_client_key p) { this.payload = p; }
        private PTuple_client_key payload;
        public PTuple_client_key getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eReadTransReq
    public static class eReadTransResp implements PObserveEvent.PEvent<PTuple_key_val_status> {
        public eReadTransResp(PTuple_key_val_status p) { this.payload = p; }
        private PTuple_key_val_status payload;
        public PTuple_key_val_status getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eReadTransResp
    public static class ePrepareReq implements PObserveEvent.PEvent<PTuple_key_val_transId> {
        public ePrepareReq(PTuple_key_val_transId p) { this.payload = p; }
        private PTuple_key_val_transId payload;
        public PTuple_key_val_transId getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for ePrepareReq
    public static class ePrepareResp implements PObserveEvent.PEvent<PTuple_participant_transId_status> {
        public ePrepareResp(PTuple_participant_transId_status p) { this.payload = p; }
        private PTuple_participant_transId_status payload;
        public PTuple_participant_transId_status getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for ePrepareResp
    public static class eCommitTrans implements PObserveEvent.PEvent<Integer> {
        public eCommitTrans(int p) { this.payload = p; }
        private Integer payload;
        public Integer getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eCommitTrans
    public static class eAbortTrans implements PObserveEvent.PEvent<Integer> {
        public eAbortTrans(int p) { this.payload = p; }
        private Integer payload;
        public Integer getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eAbortTrans
    public static class eInformCoordinator implements PObserveEvent.PEvent<Long> {
        public eInformCoordinator(long p) { this.payload = p; }
        private Long payload;
        public Long getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eInformCoordinator
    public static class eMonitor_AtomicityInitialize implements PObserveEvent.PEvent<Integer> {
        public eMonitor_AtomicityInitialize(int p) { this.payload = p; }
        private Integer payload;
        public Integer getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eMonitor_AtomicityInitialize
    public static class eStartTimer implements PObserveEvent.PEvent<Void> {
        public eStartTimer() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eStartTimer
    public static class eCancelTimer implements PObserveEvent.PEvent<Void> {
        public eCancelTimer() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eCancelTimer
    public static class eTimeOut implements PObserveEvent.PEvent<Void> {
        public eTimeOut() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eTimeOut
    public static class eDelayedTimeOut implements PObserveEvent.PEvent<Void> {
        public eDelayedTimeOut() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eDelayedTimeOut
    public static class eDelayNodeFailure implements PObserveEvent.PEvent<Void> {
        public eDelayNodeFailure() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eDelayNodeFailure
    public static class eShutDown implements PObserveEvent.PEvent<Long> {
        public eShutDown(long p) { this.payload = p; }
        private Long payload;
        public Long getPayload() { return payload; }

        @Override
        public boolean equals(Object o) { return Values.deepEquals(this, o); }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eShutDown

    // PMachine Coordinator elided
    // PMachine Participant elided
    public static class AtomicityInvariant extends Monitor {
        private HashMap<Integer,HashMap<Integer,Integer>> participantsResponse = new HashMap<Integer,HashMap<Integer,Integer>>();
        public HashMap<Integer,HashMap<Integer,Integer>> getParticipantsResponse() { return this.participantsResponse; };

        private int numParticipants = 0;
        public int getNumParticipants() { return this.numParticipants; };


        public String INIT_STATE = "Init";
        public String WAITFOREVENTS_STATE = "WaitForEvents";

        private void Anon(int n) {

            numParticipants = n;
        }
        private void Anon_1(PTuple_participant_transId_status resp) {
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
                participantsResponse.get(transId).put(tTransStatus.SUCCESS,0);
                participantsResponse.get(transId).put(tTransStatus.ERROR,0);
            }
            TMP_tmp5 = resp.status;
            TMP_tmp6 = participantsResponse.get(transId);
            TMP_tmp7 = resp.status;
            TMP_tmp8 = TMP_tmp6.get(TMP_tmp7);
            TMP_tmp9 = TMP_tmp8 + 1;
            participantsResponse.get(transId).put(TMP_tmp5,TMP_tmp9);
        }
        private void Anon_2(PTuple_transId_status resp_1) {
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
                TMP_tmp3_1 = TMP_tmp2_1 == tTransStatus.TIMEOUT;
                TMP_tmp4_1 = TMP_tmp3_1;
            }
            TMP_tmp5_1 = "Write transaction was responded to the client without receiving any responses from the participants!";
            tryAssert(TMP_tmp4_1, TMP_tmp5_1);
            TMP_tmp6_1 = resp_1.status;
            TMP_tmp7_1 = TMP_tmp6_1 == tTransStatus.SUCCESS;
            if (TMP_tmp7_1) {
                TMP_tmp8_1 = resp_1.transId;
                TMP_tmp9_1 = participantsResponse.get(TMP_tmp8_1);
                TMP_tmp10 = TMP_tmp9_1.get(tTransStatus.SUCCESS);
                TMP_tmp11 = TMP_tmp10 == numParticipants;
                TMP_tmp12 = "Write transaction was responded as committed before receiving success from all participants. ";
                TMP_tmp13 = resp_1.transId;
                TMP_tmp14 = participantsResponse.get(TMP_tmp13);
                TMP_tmp15 = TMP_tmp14.get(tTransStatus.SUCCESS);
                TMP_tmp16 = resp_1.transId;
                TMP_tmp17 = participantsResponse.get(TMP_tmp16);
                TMP_tmp18 = TMP_tmp17.get(tTransStatus.ERROR);
                TMP_tmp19 = MessageFormat.format("participants sent success: {0}, participants sent error: {1}", TMP_tmp15, TMP_tmp18);
                TMP_tmp20 = TMP_tmp12 + TMP_tmp19;
                tryAssert(TMP_tmp11, TMP_tmp20);
            }
            else
            {
                TMP_tmp21 = resp_1.status;
                TMP_tmp22 = TMP_tmp21 == tTransStatus.ERROR;
                if (TMP_tmp22) {
                    TMP_tmp23 = resp_1.transId;
                    TMP_tmp24 = participantsResponse.get(TMP_tmp23);
                    TMP_tmp25 = TMP_tmp24.get(tTransStatus.ERROR);
                    TMP_tmp26 = TMP_tmp25 > 0;
                    TMP_tmp27 = resp_1.transId;
                    TMP_tmp28 = MessageFormat.format("Write transaction {0} was responded as failed before receiving error from atleast one participant.", TMP_tmp27);
                    TMP_tmp29 = resp_1.transId;
                    TMP_tmp30 = participantsResponse.get(TMP_tmp29);
                    TMP_tmp31 = TMP_tmp30.get(tTransStatus.SUCCESS);
                    TMP_tmp32 = resp_1.transId;
                    TMP_tmp33 = participantsResponse.get(TMP_tmp32);
                    TMP_tmp34 = TMP_tmp33.get(tTransStatus.ERROR);
                    TMP_tmp35 = MessageFormat.format("participants sent success: {0}, participants sent error: {1}", TMP_tmp31, TMP_tmp34);
                    TMP_tmp36 = TMP_tmp28 + TMP_tmp35;
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
    public static class Progress extends Monitor {
        private int pendingTransactions = 0;
        public int getPendingTransactions() { return this.pendingTransactions; };


        public String INIT_STATE = "Init";
        public String WAITFORRESPONSES_STATE = "WaitForResponses";
        public String ALLTRANSACTIONSFINISHED_STATE = "AllTransactionsFinished";

        private void Anon_3() {
            int TMP_tmp0_2 = 0;

            TMP_tmp0_2 = pendingTransactions + 1;
            pendingTransactions = TMP_tmp0_2;
        }
        private void Anon_4()throws TransitionException {
            int TMP_tmp0_3 = 0;
            boolean TMP_tmp1_2 = false;

            TMP_tmp0_3 = pendingTransactions - 1;
            pendingTransactions = TMP_tmp0_3;
            TMP_tmp1_2 = pendingTransactions == 0;
            if (TMP_tmp1_2) {
                gotoState(ALLTRANSACTIONSFINISHED_STATE);
                return;
            }
        }
        private void Anon_5() {
            int TMP_tmp0_4 = 0;

            TMP_tmp0_4 = pendingTransactions + 1;
            pendingTransactions = TMP_tmp0_4;
        }
        private void Anon_6() {
            int TMP_tmp0_5 = 0;

            TMP_tmp0_5 = pendingTransactions + 1;
            pendingTransactions = TMP_tmp0_5;
        }

        public Progress() {
            super();
            addState(new State.Builder(INIT_STATE)
                    .isInitialState(true)
                    .withEvent(eWriteTransReq.class, __ -> { Anon_3(); gotoState(WAITFORRESPONSES_STATE); })
                    .build());
            addState(new State.Builder(WAITFORRESPONSES_STATE)
                    .isInitialState(false)
                    .withEvent(eWriteTransResp.class, __ -> Anon_4())
                    .withEvent(eWriteTransReq.class, __ -> Anon_5())
                    .build());
            addState(new State.Builder(ALLTRANSACTIONSFINISHED_STATE)
                    .isInitialState(false)
                    .withEvent(eWriteTransReq.class, __ -> { Anon_6(); gotoState(WAITFORRESPONSES_STATE); })
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
