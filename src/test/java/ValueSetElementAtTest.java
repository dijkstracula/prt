import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

import prt.*;

/** Tests `Values.setElementAt()`. */
public class ValueSetElementAtTest {

    @Test
    @DisplayName("Throws on negative index")
    public void testNegativeIndex() {
        LinkedHashSet<Integer> s = new LinkedHashSet<>(List.of(1,2,3,4,5));
        assertThrows(NoSuchElementException.class, () -> Values.setElementAt(s, -1));
    }

    @Test
    @DisplayName("Throws on out-of-bounds index")
    public void testOOB() {
        LinkedHashSet<Integer> s = new LinkedHashSet<>(List.of(1,2,3,4,5));
        assertThrows(NoSuchElementException.class, () -> Values.setElementAt(s, 5));
    }

    @Test
    @DisplayName("Throws on an empty set")
    public void testEmptySet() {
        LinkedHashSet<Integer> s = new LinkedHashSet<>();
        assertThrows(NoSuchElementException.class, () -> Values.setElementAt(s, 0));
    }

    @Test
    @DisplayName("Returns valid elements on valid indices")
    public void testValidIndexing() {
        LinkedHashSet<Integer> s = new LinkedHashSet<>(List.of(1,2,3,4,5));

        assertEquals(Values.setElementAt(s, 0), 1);
        assertEquals(Values.setElementAt(s, 1), 2);
        assertEquals(Values.setElementAt(s, 2), 3);
        assertEquals(Values.setElementAt(s, 3), 4);
        assertEquals(Values.setElementAt(s, 4), 5);
    }
}
