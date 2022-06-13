import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prt.Values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Validates Value.compare() and Value.equals().
 */
public class ValueCompareTest {

    @Test
    @DisplayName("compare() is well-defined for nulls")
    public void testNullComparisons() {
        Integer i = 42;
        assertEquals(Values.compare(null, i), -1);
        assertEquals(Values.compare(null, null), 0);
        assertEquals(Values.compare(i, null), 1);
    }

    @Test
    @DisplayName("compare() boxed primitive types")
    public void testPrimitiveComparisons() {
        assertEquals(Values.compare(999, 42), 1);
        assertEquals(Values.compare(42, 42), 0);
        assertEquals(Values.compare(49, 999), -1);

        assertEquals(Values.compare(999L, 42L), 1);
        assertEquals(Values.compare(42L, 42L), 0);
        assertEquals(Values.compare(49L, 999L), -1);

        assertEquals(Values.compare(3.14, 2.71), 1);
        assertEquals(Values.compare(2.71, 2.71), 0);
        assertEquals(Values.compare(2.71, 3.14), -1);

        assertEquals(Values.compare("za", "az"), 1);
        assertEquals(Values.compare("a", "a"), 0);
        assertEquals(Values.compare("az", "za"), -1);
    }

    @Test
    @DisplayName("equals() is well-defined for nulls")
    public void testNullEquality() {
        Object o = new Object();
        assertFalse(Values.deepEquals(o, null));
        assertTrue(Values.deepEquals(null, null));
        assertFalse(Values.deepEquals(null, o));
    }

    @Test
    @DisplayName("equals() does not coerse numeric types")
    public void testNoCorersionForEquality() {
        // int <-> bool
        assertFalse(Values.deepEquals(0, false));
        assertFalse(Values.deepEquals(1, true));

        // int <-> float
        assertFalse(Values.deepEquals(0, 0.0));
        assertFalse(Values.deepEquals(42, 42.0f));

        // int <-> long
        assertFalse(Values.deepEquals(0, 0L));
        assertFalse(Values.deepEquals(42, 42L));
    }

    @Test
    @DisplayName("equals() correctly does deep equality checks")
    public void testDeepEquality() {
        HashMap<String, ArrayList<Integer>> m1 = new HashMap<>(Map.of("123", new ArrayList<>(List.of(1, 2, 3))));
        HashMap<String, ArrayList<Integer>> m2 = new HashMap<>(Map.of("123", new ArrayList<>(List.of(1, 2, 3))));

        assertFalse(m1.get("123") == m2.get("123")); // Ensure that the values are different references
        assertTrue(Values.deepEquals(m1, m2));
    }
}
