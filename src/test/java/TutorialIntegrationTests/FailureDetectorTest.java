package TutorialIntegrationTests;


import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prt.Monitor;
import prt.PAssertionFailureException;
import prt.UnhandledEventException;

import static org.junit.jupiter.api.Assertions.*;

import static TutorialIntegrationTests.FailureDetector.*;

public class FailureDetectorTest {
    @Test
    @DisplayName("Can notify nodes down")
    void testCanNotifyNodesDown() {
        ReliableFailureDetector m  = new ReliableFailureDetector();
        m.ready();

        assertEquals(0, m.getNodesDownDetected().size());
        assertEquals(0, m.getNodesShutdownAndNotDetected().size());
        m.process(new eShutDown(1L));
        assertEquals(0, m.getNodesDownDetected().size());
        assertEquals(1, m.getNodesShutdownAndNotDetected().size());

        LinkedHashSet<Long> nodes = new LinkedHashSet<>(Set.of(1L, 2L, 3L));
        m.process(new eNotifyNodesDown(nodes));
        assertEquals(3, m.getNodesDownDetected().size());
        assertEquals(0, m.getNodesShutdownAndNotDetected().size());
    }
}
