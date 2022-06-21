package tutorialmonitors;

import java.text.MessageFormat;
import java.util.*;

import events.PObserveEvent;
import prt.*;

/***************************************************************************
 * This file was auto-generated on Monday, 20 June 2022 at 16:59:34.
 * Please do not edit manually!
 **************************************************************************/

public class ClientServer {
    /** Enums */
    public static class tWithDrawRespStatus {
        public static final int WITHDRAW_SUCCESS = 0;
        public static final int WITHDRAW_ERROR = 1;
    }

    /** Tuples */
    // (accountId:int,balance:int)
    public static class PTuple_accountId_balance implements Values.PTuple<PTuple_accountId_balance> {
        public int accountId;
        public int balance;

        public PTuple_accountId_balance() {
            this.accountId = 0;
            this.balance = 0;
        }

        public PTuple_accountId_balance(int accountId, int balance) {
            this.accountId = accountId;
            this.balance = balance;
        }

        public PTuple_accountId_balance deepClone() {
            return new PTuple_accountId_balance(accountId, balance);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_accountId_balance)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_accountId_balance other) {
            return (true
                    && this.accountId == other.accountId
                    && this.balance == other.balance
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_accountId_balance");
            sb.append("[");
            sb.append("accountId=" + accountId);
            sb.append(",balance=" + balance);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_accountId_balance class definition

    // (accountId:int)
    public static class PTuple_accountId implements Values.PTuple<PTuple_accountId> {
        public int accountId;

        public PTuple_accountId() {
            this.accountId = 0;
        }

        public PTuple_accountId(int accountId) {
            this.accountId = accountId;
        }

        public PTuple_accountId deepClone() {
            return new PTuple_accountId(accountId);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_accountId)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_accountId other) {
            return (true
                    && this.accountId == other.accountId
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_accountId");
            sb.append("[");
            sb.append("accountId=" + accountId);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_accountId class definition

    // (source:Client,accountId:int,amount:int,rId:int)
    public static class PTuple_source_accountId_amount_rId implements Values.PTuple<PTuple_source_accountId_amount_rId> {
        public long source;
        public int accountId;
        public int amount;
        public int rId;

        public PTuple_source_accountId_amount_rId() {
            this.source = 0L;
            this.accountId = 0;
            this.amount = 0;
            this.rId = 0;
        }

        public PTuple_source_accountId_amount_rId(long source, int accountId, int amount, int rId) {
            this.source = source;
            this.accountId = accountId;
            this.amount = amount;
            this.rId = rId;
        }

        public PTuple_source_accountId_amount_rId deepClone() {
            return new PTuple_source_accountId_amount_rId(source, accountId, amount, rId);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_source_accountId_amount_rId)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_source_accountId_amount_rId other) {
            return (true
                    && this.source == other.source
                    && this.accountId == other.accountId
                    && this.amount == other.amount
                    && this.rId == other.rId
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_source_accountId_amount_rId");
            sb.append("[");
            sb.append("source=" + source);
            sb.append(",accountId=" + accountId);
            sb.append(",amount=" + amount);
            sb.append(",rId=" + rId);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_source_accountId_amount_rId class definition

    // (status:tWithDrawRespStatus,accountId:int,balance:int,rId:int)
    public static class PTuple_status_accountId_balance_rId implements Values.PTuple<PTuple_status_accountId_balance_rId> {
        public int status;
        public int accountId;
        public int balance;
        public int rId;

        public PTuple_status_accountId_balance_rId() {
            this.status = 0;
            this.accountId = 0;
            this.balance = 0;
            this.rId = 0;
        }

        public PTuple_status_accountId_balance_rId(int status, int accountId, int balance, int rId) {
            this.status = status;
            this.accountId = accountId;
            this.balance = balance;
            this.rId = rId;
        }

        public PTuple_status_accountId_balance_rId deepClone() {
            return new PTuple_status_accountId_balance_rId(status, accountId, balance, rId);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_status_accountId_balance_rId)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_status_accountId_balance_rId other) {
            return (true
                    && this.status == other.status
                    && this.accountId == other.accountId
                    && this.balance == other.balance
                    && this.rId == other.rId
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_status_accountId_balance_rId");
            sb.append("[");
            sb.append("status=" + status);
            sb.append(",accountId=" + accountId);
            sb.append(",balance=" + balance);
            sb.append(",rId=" + rId);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_status_accountId_balance_rId class definition


    /** Events */
    public static class DefaultEvent extends PObserveEvent.PEvent<Void> {
        public DefaultEvent() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "DefaultEvent";
        } // toString()

    } // PEvent definition for DefaultEvent
    public static class PHalt extends PObserveEvent.PEvent<Void> {
        public PHalt() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public String toString() {
            return "PHalt";
        } // toString()

    } // PEvent definition for PHalt
    public static class eUpdateQuery extends PObserveEvent.PEvent<PTuple_accountId_balance> {
        public eUpdateQuery(PTuple_accountId_balance p) { this.payload = p; }
        private PTuple_accountId_balance payload;
        public PTuple_accountId_balance getPayload() { return payload; }

        @Override
        public String toString() {
            return "eUpdateQuery[" + payload + "]";
        } // toString()

    } // PEvent definition for eUpdateQuery
    public static class eReadQuery extends PObserveEvent.PEvent<PTuple_accountId> {
        public eReadQuery(PTuple_accountId p) { this.payload = p; }
        private PTuple_accountId payload;
        public PTuple_accountId getPayload() { return payload; }

        @Override
        public String toString() {
            return "eReadQuery[" + payload + "]";
        } // toString()

    } // PEvent definition for eReadQuery
    public static class eReadQueryResp extends PObserveEvent.PEvent<PTuple_accountId_balance> {
        public eReadQueryResp(PTuple_accountId_balance p) { this.payload = p; }
        private PTuple_accountId_balance payload;
        public PTuple_accountId_balance getPayload() { return payload; }

        @Override
        public String toString() {
            return "eReadQueryResp[" + payload + "]";
        } // toString()

    } // PEvent definition for eReadQueryResp
    public static class eWithDrawReq extends PObserveEvent.PEvent<PTuple_source_accountId_amount_rId> {
        public eWithDrawReq(PTuple_source_accountId_amount_rId p) { this.payload = p; }
        private PTuple_source_accountId_amount_rId payload;
        public PTuple_source_accountId_amount_rId getPayload() { return payload; }

        @Override
        public String toString() {
            return "eWithDrawReq[" + payload + "]";
        } // toString()

    } // PEvent definition for eWithDrawReq
    public static class eWithDrawResp extends PObserveEvent.PEvent<PTuple_status_accountId_balance_rId> {
        public eWithDrawResp(PTuple_status_accountId_balance_rId p) { this.payload = p; }
        private PTuple_status_accountId_balance_rId payload;
        public PTuple_status_accountId_balance_rId getPayload() { return payload; }

        @Override
        public String toString() {
            return "eWithDrawResp[" + payload + "]";
        } // toString()

    } // PEvent definition for eWithDrawResp
    public static class eSpec_BankBalanceIsAlwaysCorrect_Init extends PObserveEvent.PEvent<HashMap<Integer,Integer>> {
        public eSpec_BankBalanceIsAlwaysCorrect_Init(HashMap<Integer,Integer> p) { this.payload = p; }
        private HashMap<Integer,Integer> payload;
        public HashMap<Integer,Integer> getPayload() { return payload; }

        @Override
        public String toString() {
            return "eSpec_BankBalanceIsAlwaysCorrect_Init[" + payload + "]";
        } // toString()

    } // PEvent definition for eSpec_BankBalanceIsAlwaysCorrect_Init

    // PMachine BankServer elided
    // PMachine Database elided
    // PMachine Client elided
    // PMachine AbstractBankServer elided
    public static class BankBalanceIsAlwaysCorrect extends Monitor {
        private HashMap<Integer,Integer> bankBalance = new HashMap<Integer,Integer>();
        public HashMap<Integer,Integer> getBankBalance() { return this.bankBalance; };

        private HashMap<Integer,PTuple_source_accountId_amount_rId> pendingWithDraws = new HashMap<Integer,PTuple_source_accountId_amount_rId>();
        public HashMap<Integer,PTuple_source_accountId_amount_rId> getPendingWithDraws() { return this.pendingWithDraws; };


        public String INIT_STATE = "Init";
        public String WAITFORWITHDRAWREQANDRESP_STATE = "WaitForWithDrawReqAndResp";

        private void Anon(HashMap<Integer,Integer> balance) {

            bankBalance = (HashMap<Integer,Integer>)Values.deepClone(balance);
        }
        private void Anon_1(PTuple_source_accountId_amount_rId req) {
            int TMP_tmp0 = 0;
            boolean TMP_tmp1 = false;
            int TMP_tmp2 = 0;
            ArrayList<Integer> TMP_tmp3 = new ArrayList<Integer>();
            String TMP_tmp4 = "";
            int TMP_tmp5 = 0;

            TMP_tmp0 = req.accountId;
            TMP_tmp1 = bankBalance.containsKey(TMP_tmp0);
            TMP_tmp2 = req.accountId;
            TMP_tmp3 = new ArrayList<Integer>(bankBalance.keySet());
            TMP_tmp4 = MessageFormat.format("Unknown accountId {0} in the withdraw request. Valid accountIds = {1}", TMP_tmp2, TMP_tmp3);
            tryAssert(TMP_tmp1, TMP_tmp4);
            TMP_tmp5 = req.rId;
            pendingWithDraws.put(TMP_tmp5,req.deepClone());
        }
        private void Anon_2(PTuple_status_accountId_balance_rId resp) {
            int TMP_tmp0_1 = 0;
            boolean TMP_tmp1_1 = false;
            int TMP_tmp2_1 = 0;
            String TMP_tmp3_1 = "";
            int TMP_tmp4_1 = 0;
            boolean TMP_tmp5_1 = false;
            int TMP_tmp6 = 0;
            String TMP_tmp7 = "";
            int TMP_tmp8 = 0;
            boolean TMP_tmp9 = false;
            String TMP_tmp10 = "";
            int TMP_tmp11 = 0;
            boolean TMP_tmp12 = false;
            int TMP_tmp13 = 0;
            int TMP_tmp14 = 0;
            int TMP_tmp15 = 0;
            int TMP_tmp16 = 0;
            PTuple_source_accountId_amount_rId TMP_tmp17 = new PTuple_source_accountId_amount_rId();
            int TMP_tmp18 = 0;
            int TMP_tmp19 = 0;
            boolean TMP_tmp20 = false;
            int TMP_tmp21 = 0;
            int TMP_tmp22 = 0;
            int TMP_tmp23 = 0;
            int TMP_tmp24 = 0;
            int TMP_tmp25 = 0;
            PTuple_source_accountId_amount_rId TMP_tmp26 = new PTuple_source_accountId_amount_rId();
            int TMP_tmp27 = 0;
            int TMP_tmp28 = 0;
            String TMP_tmp29 = "";
            int TMP_tmp30 = 0;
            int TMP_tmp31 = 0;
            int TMP_tmp32 = 0;
            int TMP_tmp33 = 0;
            int TMP_tmp34 = 0;
            int TMP_tmp35 = 0;
            PTuple_source_accountId_amount_rId TMP_tmp36 = new PTuple_source_accountId_amount_rId();
            int TMP_tmp37 = 0;
            int TMP_tmp38 = 0;
            boolean TMP_tmp39 = false;
            int TMP_tmp40 = 0;
            PTuple_source_accountId_amount_rId TMP_tmp41 = new PTuple_source_accountId_amount_rId();
            int TMP_tmp42 = 0;
            int TMP_tmp43 = 0;
            int TMP_tmp44 = 0;
            String TMP_tmp45 = "";
            int TMP_tmp46 = 0;
            int TMP_tmp47 = 0;
            int TMP_tmp48 = 0;
            boolean TMP_tmp49 = false;
            int TMP_tmp50 = 0;
            int TMP_tmp51 = 0;
            int TMP_tmp52 = 0;
            String TMP_tmp53 = "";

            TMP_tmp0_1 = resp.accountId;
            TMP_tmp1_1 = bankBalance.containsKey(TMP_tmp0_1);
            TMP_tmp2_1 = resp.accountId;
            TMP_tmp3_1 = MessageFormat.format("Unknown accountId {0} in the withdraw response!", TMP_tmp2_1);
            tryAssert(TMP_tmp1_1, TMP_tmp3_1);
            TMP_tmp4_1 = resp.rId;
            TMP_tmp5_1 = pendingWithDraws.containsKey(TMP_tmp4_1);
            TMP_tmp6 = resp.rId;
            TMP_tmp7 = MessageFormat.format("Unknown rId {0} in the withdraw response!", TMP_tmp6);
            tryAssert(TMP_tmp5_1, TMP_tmp7);
            TMP_tmp8 = resp.balance;
            TMP_tmp9 = TMP_tmp8 >= 10;
            TMP_tmp10 = "Bank balance in all accounts must always be greater than or equal to 10!!";
            tryAssert(TMP_tmp9, TMP_tmp10);
            TMP_tmp11 = resp.status;
            TMP_tmp12 = TMP_tmp11 == tWithDrawRespStatus.WITHDRAW_SUCCESS;
            if (TMP_tmp12) {
                TMP_tmp13 = resp.balance;
                TMP_tmp14 = resp.accountId;
                TMP_tmp15 = bankBalance.get(TMP_tmp14);
                TMP_tmp16 = resp.rId;
                TMP_tmp17 = pendingWithDraws.get(TMP_tmp16);
                TMP_tmp18 = TMP_tmp17.amount;
                TMP_tmp19 = TMP_tmp15 - TMP_tmp18;
                TMP_tmp20 = TMP_tmp13 == TMP_tmp19;
                TMP_tmp21 = resp.accountId;
                TMP_tmp22 = resp.balance;
                TMP_tmp23 = resp.accountId;
                TMP_tmp24 = bankBalance.get(TMP_tmp23);
                TMP_tmp25 = resp.rId;
                TMP_tmp26 = pendingWithDraws.get(TMP_tmp25);
                TMP_tmp27 = TMP_tmp26.amount;
                TMP_tmp28 = TMP_tmp24 - TMP_tmp27;
                TMP_tmp29 = MessageFormat.format("Bank balance for the account {0} is {1} and not the expected value {2}, Bank is lying!", TMP_tmp21, TMP_tmp22, TMP_tmp28);
                tryAssert(TMP_tmp20, TMP_tmp29);
                TMP_tmp30 = resp.accountId;
                TMP_tmp31 = resp.balance;
                TMP_tmp32 = TMP_tmp31;
                bankBalance.put(TMP_tmp30,TMP_tmp32);
            }
            else
            {
                TMP_tmp33 = resp.accountId;
                TMP_tmp34 = bankBalance.get(TMP_tmp33);
                TMP_tmp35 = resp.rId;
                TMP_tmp36 = pendingWithDraws.get(TMP_tmp35);
                TMP_tmp37 = TMP_tmp36.amount;
                TMP_tmp38 = TMP_tmp34 - TMP_tmp37;
                TMP_tmp39 = TMP_tmp38 < 10;
                TMP_tmp40 = resp.rId;
                TMP_tmp41 = pendingWithDraws.get(TMP_tmp40);
                TMP_tmp42 = TMP_tmp41.amount;
                TMP_tmp43 = resp.accountId;
                TMP_tmp44 = bankBalance.get(TMP_tmp43);
                TMP_tmp45 = MessageFormat.format("Bank must accept the withdraw request for {0}, bank balance is {1}!", TMP_tmp42, TMP_tmp44);
                tryAssert(TMP_tmp39, TMP_tmp45);
                TMP_tmp46 = resp.accountId;
                TMP_tmp47 = bankBalance.get(TMP_tmp46);
                TMP_tmp48 = resp.balance;
                TMP_tmp49 = TMP_tmp47 == TMP_tmp48;
                TMP_tmp50 = resp.accountId;
                TMP_tmp51 = bankBalance.get(TMP_tmp50);
                TMP_tmp52 = resp.balance;
                TMP_tmp53 = MessageFormat.format("Withdraw failed BUT the account balance changed! actual: {0}, bank said: {1}", TMP_tmp51, TMP_tmp52);
                tryAssert(TMP_tmp49, TMP_tmp53);
            }
        }

        public BankBalanceIsAlwaysCorrect() {
            super();
            addState(new State.Builder(INIT_STATE)
                    .isInitialState(true)
                    .withEvent(eSpec_BankBalanceIsAlwaysCorrect_Init.class, p -> { Anon(p); gotoState(WAITFORWITHDRAWREQANDRESP_STATE); })
                    .build());
            addState(new State.Builder(WAITFORWITHDRAWREQANDRESP_STATE)
                    .isInitialState(false)
                    .withEvent(eWithDrawReq.class, this::Anon_1)
                    .withEvent(eWithDrawResp.class, this::Anon_2)
                    .build());
        } // constructor
    } // BankBalanceIsAlwaysCorrect monitor definition
    public static class GuaranteedWithDrawProgress extends Monitor {
        private LinkedHashSet<Integer> pendingWDReqs = new LinkedHashSet<Integer>();
        public LinkedHashSet<Integer> getPendingWDReqs() { return this.pendingWDReqs; };


        public String NOPENDINGREQUESTS_STATE = "NopendingRequests";
        public String PENDINGREQS_STATE = "PendingReqs";

        private void Anon_3(PTuple_source_accountId_amount_rId req_1) {
            int TMP_tmp0_2 = 0;

            TMP_tmp0_2 = req_1.rId;
            pendingWDReqs.add(TMP_tmp0_2);
        }
        private void Anon_4(PTuple_status_accountId_balance_rId resp_1)throws TransitionException {
            int TMP_tmp0_3 = 0;
            boolean TMP_tmp1_2 = false;
            int TMP_tmp2_2 = 0;
            LinkedHashSet<Integer> TMP_tmp3_2 = new LinkedHashSet<Integer>();
            String TMP_tmp4_2 = "";
            int TMP_tmp5_2 = 0;
            int TMP_tmp6_1 = 0;
            boolean TMP_tmp7_1 = false;

            TMP_tmp0_3 = resp_1.rId;
            TMP_tmp1_2 = pendingWDReqs.contains(TMP_tmp0_3);
            TMP_tmp2_2 = resp_1.rId;
            TMP_tmp3_2 = (LinkedHashSet<Integer>)Values.deepClone(pendingWDReqs);
            TMP_tmp4_2 = MessageFormat.format("unexpected rId: {0} received, expected one of {1}", TMP_tmp2_2, TMP_tmp3_2);
            tryAssert(TMP_tmp1_2, TMP_tmp4_2);
            TMP_tmp5_2 = resp_1.rId;
            pendingWDReqs.remove(TMP_tmp5_2);
            TMP_tmp6_1 = pendingWDReqs.size();
            TMP_tmp7_1 = TMP_tmp6_1 == 0;
            if (TMP_tmp7_1) {
                gotoState(NOPENDINGREQUESTS_STATE);
                return;
            }
        }
        private void Anon_5(PTuple_source_accountId_amount_rId req_2) {
            int TMP_tmp0_4 = 0;

            TMP_tmp0_4 = req_2.rId;
            pendingWDReqs.add(TMP_tmp0_4);
        }

        public GuaranteedWithDrawProgress() {
            super();
            addState(new State.Builder(NOPENDINGREQUESTS_STATE)
                    .isInitialState(true)
                    .withEvent(eWithDrawReq.class, p -> { Anon_3(p); gotoState(PENDINGREQS_STATE); })
                    .build());
            addState(new State.Builder(PENDINGREQS_STATE)
                    .isInitialState(false)
                    .withEvent(eWithDrawResp.class, this::Anon_4)
                    .withEvent(eWithDrawReq.class, p -> { Anon_5(p); gotoState(PENDINGREQS_STATE); })
                    .build());
        } // constructor
    } // GuaranteedWithDrawProgress monitor definition
    // PMachine TestWithSingleClient elided
    // PMachine TestWithMultipleClients elided
} // ClientServer.java class definition
