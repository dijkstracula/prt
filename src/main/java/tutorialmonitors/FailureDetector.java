package tutorialmonitors;

import java.util.LinkedHashSet;
import java.util.Objects;

import events.PObserveEvent;
import prt.*;

/***************************************************************************
 * This file was auto-generated on Friday, 17 June 2022 at 14:33:58.
 * Please do not edit manually!
 **************************************************************************/

public class FailureDetector {
    /** Enums */

    /** Tuples */
    // (fd:FailureDetector,trial:int)
    public static class PTuple_fd_trial implements Values.PTuple<PTuple_fd_trial> {
        public long fd;
        public int trial;

        public PTuple_fd_trial() {
            this.fd = 0L;
            this.trial = 0;
        }

        public PTuple_fd_trial(long fd, int trial) {
            this.fd = fd;
            this.trial = trial;
        }

        public PTuple_fd_trial deepClone() {
            return new PTuple_fd_trial(fd, trial);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_fd_trial)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_fd_trial other) {
            return (true
                    && this.fd == other.fd
                    && this.trial == other.trial
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_fd_trial");
            sb.append("[");
            sb.append("fd=" + fd);
            sb.append(",trial=" + trial);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_fd_trial class definition

    // (node:Node,trial:int)
    public static class PTuple_node_trial implements Values.PTuple<PTuple_node_trial> {
        public long node;
        public int trial;

        public PTuple_node_trial() {
            this.node = 0L;
            this.trial = 0;
        }

        public PTuple_node_trial(long node, int trial) {
            this.node = node;
            this.trial = trial;
        }

        public PTuple_node_trial deepClone() {
            return new PTuple_node_trial(node, trial);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_node_trial)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_node_trial other) {
            return (true
                    && this.node == other.node
                    && this.trial == other.trial
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_node_trial");
            sb.append("[");
            sb.append("node=" + node);
            sb.append(",trial=" + trial);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_node_trial class definition

    // (numNodes:int,numClients:int)
    public static class PTuple_numNodes_numClients implements Values.PTuple<PTuple_numNodes_numClients> {
        public int numNodes;
        public int numClients;

        public PTuple_numNodes_numClients() {
            this.numNodes = 0;
            this.numClients = 0;
        }

        public PTuple_numNodes_numClients(int numNodes, int numClients) {
            this.numNodes = numNodes;
            this.numClients = numClients;
        }

        public PTuple_numNodes_numClients deepClone() {
            return new PTuple_numNodes_numClients(numNodes, numClients);
        } // deepClone()

        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() &&
                    this.deepEquals((PTuple_numNodes_numClients)other)
            );
        } // equals()

        public boolean deepEquals(PTuple_numNodes_numClients other) {
            return (true
                    && this.numNodes == other.numNodes
                    && this.numClients == other.numClients
            );
        } // deepEquals()

        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_numNodes_numClients");
            sb.append("[");
            sb.append("numNodes=" + numNodes);
            sb.append(",numClients=" + numClients);
            sb.append("]");
            return sb.toString();
        } // toString()
    } //PTuple_numNodes_numClients class definition

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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DefaultEvent that = (DefaultEvent)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for DefaultEvent
    public static class PHalt implements PObserveEvent.PEvent<Void> {
        public PHalt() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PHalt that = (PHalt)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for PHalt
    public static class ePing implements PObserveEvent.PEvent<PTuple_fd_trial> {
        public ePing(PTuple_fd_trial p) { this.payload = p; }
        private PTuple_fd_trial payload;
        public PTuple_fd_trial getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ePing that = (ePing)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for ePing
    public static class ePong implements PObserveEvent.PEvent<PTuple_node_trial> {
        public ePong(PTuple_node_trial p) { this.payload = p; }
        private PTuple_node_trial payload;
        public PTuple_node_trial getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ePong that = (ePong)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for ePong
    public static class eNotifyNodesDown implements PObserveEvent.PEvent<LinkedHashSet<Long>> {
        public eNotifyNodesDown(LinkedHashSet<Long> p) { this.payload = p; }
        private LinkedHashSet<Long> payload;
        public LinkedHashSet<Long> getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eNotifyNodesDown that = (eNotifyNodesDown)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eNotifyNodesDown
    public static class eStartTimer implements PObserveEvent.PEvent<Void> {
        public eStartTimer() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eStartTimer that = (eStartTimer)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eStartTimer
    public static class eCancelTimer implements PObserveEvent.PEvent<Void> {
        public eCancelTimer() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eCancelTimer that = (eCancelTimer)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eCancelTimer
    public static class eTimeOut implements PObserveEvent.PEvent<Void> {
        public eTimeOut() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eTimeOut that = (eTimeOut)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eTimeOut
    public static class eDelayedTimeOut implements PObserveEvent.PEvent<Void> {
        public eDelayedTimeOut() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eDelayedTimeOut that = (eDelayedTimeOut)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eDelayedTimeOut
    public static class eDelayNodeFailure implements PObserveEvent.PEvent<Void> {
        public eDelayNodeFailure() { }
        private Void payload;
        public Void getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eDelayNodeFailure that = (eDelayNodeFailure)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eDelayNodeFailure
    public static class eShutDown implements PObserveEvent.PEvent<Long> {
        public eShutDown(long p) { this.payload = p; }
        private Long payload;
        public Long getPayload() { return payload; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            eShutDown that = (eShutDown)o;
            return Values.deepEquals(this.payload, that.payload);
        }

        @Override
        public int hashCode() { return Objects.hash(payload); }

    } // PEvent definition for eShutDown

    // PMachine Client elided
    // PMachine FailureDetector elided
    // PMachine Node elided
    public static class ReliableFailureDetector extends Monitor {
        private LinkedHashSet<Long> nodesShutdownAndNotDetected = new LinkedHashSet<Long>();
        public LinkedHashSet<Long> getNodesShutdownAndNotDetected() { return this.nodesShutdownAndNotDetected; };

        private LinkedHashSet<Long> nodesDownDetected = new LinkedHashSet<Long>();
        public LinkedHashSet<Long> getNodesDownDetected() { return this.nodesDownDetected; };


        public String ALLSHUTDOWNNODESAREDETECTED_STATE = "AllShutdownNodesAreDetected";
        public String NODESSHUTDOWNBUTNOTDETECTED_STATE = "NodesShutDownButNotDetected";

        private void Anon(LinkedHashSet<Long> nodes) {
            int i = 0;
            int TMP_tmp0 = 0;
            boolean TMP_tmp1 = false;
            boolean TMP_tmp2 = false;
            long TMP_tmp3 = 0L;
            long TMP_tmp4 = 0L;
            int TMP_tmp5 = 0;

            while ((true)) {
                TMP_tmp0 = nodes.size();
                TMP_tmp1 = i < TMP_tmp0;
                TMP_tmp2 = TMP_tmp1;
                if (TMP_tmp2) {} else
                {
                    break;
                }
                TMP_tmp3 = Values.setElementAt(nodes, i);
                nodesShutdownAndNotDetected.remove(TMP_tmp3);
                TMP_tmp4 = Values.setElementAt(nodes, i);
                nodesDownDetected.add(TMP_tmp4);
                TMP_tmp5 = i + 1;
                i = TMP_tmp5;
            }
        }
        private void Anon_1(long node)throws TransitionException {
            long TMP_tmp0_1 = 0L;
            boolean TMP_tmp1_1 = false;
            boolean TMP_tmp2_1 = false;
            long TMP_tmp3_1 = 0L;

            TMP_tmp0_1 = ((long)node);
            TMP_tmp1_1 = nodesDownDetected.contains(TMP_tmp0_1);
            TMP_tmp2_1 = !(TMP_tmp1_1);
            if (TMP_tmp2_1) {
                TMP_tmp3_1 = ((long)node);
                nodesShutdownAndNotDetected.add(TMP_tmp3_1);
                gotoState(NODESSHUTDOWNBUTNOTDETECTED_STATE);
                return;
            }
        }
        private void Anon_2(LinkedHashSet<Long> nodes_1)throws TransitionException {
            int i_1 = 0;
            int TMP_tmp0_2 = 0;
            boolean TMP_tmp1_2 = false;
            boolean TMP_tmp2_2 = false;
            long TMP_tmp3_2 = 0L;
            long TMP_tmp4_1 = 0L;
            int TMP_tmp5_1 = 0;
            int TMP_tmp6 = 0;
            boolean TMP_tmp7 = false;

            while ((true)) {
                TMP_tmp0_2 = nodes_1.size();
                TMP_tmp1_2 = i_1 < TMP_tmp0_2;
                TMP_tmp2_2 = TMP_tmp1_2;
                if (TMP_tmp2_2) {} else
                {
                    break;
                }
                TMP_tmp3_2 = Values.setElementAt(nodes_1, i_1);
                nodesShutdownAndNotDetected.remove(TMP_tmp3_2);
                TMP_tmp4_1 = Values.setElementAt(nodes_1, i_1);
                nodesDownDetected.add(TMP_tmp4_1);
                TMP_tmp5_1 = i_1 + 1;
                i_1 = TMP_tmp5_1;
            }
            TMP_tmp6 = nodesShutdownAndNotDetected.size();
            TMP_tmp7 = TMP_tmp6 == 0;
            if (TMP_tmp7) {
                gotoState(ALLSHUTDOWNNODESAREDETECTED_STATE);
                return;
            }
        }
        private void Anon_3(long node_1) {
            long TMP_tmp0_3 = 0L;
            boolean TMP_tmp1_3 = false;
            boolean TMP_tmp2_3 = false;
            long TMP_tmp3_3 = 0L;

            TMP_tmp0_3 = ((long)node_1);
            TMP_tmp1_3 = nodesDownDetected.contains(TMP_tmp0_3);
            TMP_tmp2_3 = !(TMP_tmp1_3);
            if (TMP_tmp2_3) {
                TMP_tmp3_3 = ((long)node_1);
                nodesShutdownAndNotDetected.add(TMP_tmp3_3);
            }
        }

        public ReliableFailureDetector() {
            super();
            addState(new State.Builder(ALLSHUTDOWNNODESAREDETECTED_STATE)
                    .isInitialState(true)
                    .withEvent(eNotifyNodesDown.class, this::Anon)
                    .withEvent(eShutDown.class, this::Anon_1)
                    .build());
            addState(new State.Builder(NODESSHUTDOWNBUTNOTDETECTED_STATE)
                    .isInitialState(false)
                    .withEvent(eNotifyNodesDown.class, this::Anon_2)
                    .withEvent(eShutDown.class, this::Anon_3)
                    .build());
        } // constructor
    } // ReliableFailureDetector monitor definition
    // PMachine TestMultipleClients elided
    // PMachine Timer elided
    // PMachine FailureInjector elided
} // FailureDetector.java class definition
