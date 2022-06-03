package TutorialIntegrationTests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import prt.*;


/***************************************************************************
 * This file was auto-generated on Monday, 06 June 2022 at 09:00:37.
 * Please do not edit manually!
 **************************************************************************/


public class FailureDetector {
    record DefaultEvent() implements PObserveEvent.PEvent { }
    record PHalt() implements PObserveEvent.PEvent { }
    record ePing(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record ePong(HashMap<String, Object> payload) implements PObserveEvent.PEvent { }
    record eNotifyNodesDown(LinkedHashSet<Long> payload) implements PObserveEvent.PEvent { }
    record eStartTimer() implements PObserveEvent.PEvent { }
    record eCancelTimer() implements PObserveEvent.PEvent { }
    record eTimeOut() implements PObserveEvent.PEvent { }
    record eDelayedTimeOut() implements PObserveEvent.PEvent { }
    record eDelayNodeFailure() implements PObserveEvent.PEvent { }
    record eShutDown(long payload) implements PObserveEvent.PEvent { }

    // PMachine Client elided
    // PMachine FailureDetector elided
    // PMachine Node elided
    static class ReliableFailureDetector extends Monitor {
        private LinkedHashSet<Long> nodesShutdownAndNotDetected = new LinkedHashSet<Long>();
        private LinkedHashSet<Long> nodesDownDetected = new LinkedHashSet<Long>();

        private String ALLSHUTDOWNNODESAREDETECTED_STATE = "AllShutdownNodesAreDetected";
        private String NODESSHUTDOWNBUTNOTDETECTED_STATE = "NodesShutDownButNotDetected";

        private void Anon(eNotifyNodesDown pEvent) {
            LinkedHashSet<Long> nodes = pEvent.payload;
            int i = 0;
            int TMP_tmp0 = 0;
            boolean TMP_tmp1 = false;
            boolean TMP_tmp2 = false;
            long TMP_tmp3 = 0L;
            long TMP_tmp4 = 0L;
            int TMP_tmp5 = 0;

            while ((true)) {
                TMP_tmp0 = nodes.size();
                TMP_tmp1 = (i < TMP_tmp0);
                TMP_tmp2 = TMP_tmp1;
                if (TMP_tmp2) {} else
                {
                    break;
                }
                TMP_tmp3 = (Long)Values.setElementAt(nodes, i);
                nodesShutdownAndNotDetected.remove(TMP_tmp3);
                TMP_tmp4 = (Long)Values.setElementAt(nodes, i);
                nodesDownDetected.add(TMP_tmp4);
                TMP_tmp5 = (i + 1);
                i = TMP_tmp5;
            }
        }
        private void Anon_1(eShutDown pEvent) throws TransitionException {
            long node = pEvent.payload;
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
        private void Anon_2(eNotifyNodesDown pEvent) throws TransitionException {
            LinkedHashSet<Long> nodes_1 = pEvent.payload;
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
                TMP_tmp1_2 = (i_1 < TMP_tmp0_2);
                TMP_tmp2_2 = TMP_tmp1_2;
                if (TMP_tmp2_2) {} else
                {
                    break;
                }
                TMP_tmp3_2 = (Long)Values.setElementAt(nodes_1, i_1);
                nodesShutdownAndNotDetected.remove(TMP_tmp3_2);
                TMP_tmp4_1 = (Long)Values.setElementAt(nodes_1, i_1);
                nodesDownDetected.add(TMP_tmp4_1);
                TMP_tmp5_1 = (i_1 + 1);
                i_1 = TMP_tmp5_1;
            }
            TMP_tmp6 = nodesShutdownAndNotDetected.size();
            TMP_tmp7 = (TMP_tmp6 == 0);
            if (TMP_tmp7) {
                gotoState(ALLSHUTDOWNNODESAREDETECTED_STATE);
                return;
            }
        }
        private void Anon_3(eShutDown pEvent) {
            long node_1 = pEvent.payload;
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
