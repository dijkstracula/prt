import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prt.Values;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class ValueCloneTest {

    @Test
    @DisplayName("Can 'clone' a null value")
    void testNullClone() {
        assertEquals(Values.clone(null), null);
    }

    @Test
    @DisplayName("Can clone boxed primitive types")
    void testClonePrimitives() {
        Boolean b = Boolean.valueOf(true);
        assertEquals(Values.clone(b), b);

        Integer i = Integer.valueOf(31337);
        assertEquals(Values.clone(i), i);

        Float f = Float.valueOf(1.61803f);
        assertEquals(Values.clone(f), f);

        Long l = Long.valueOf(314159265L);
        assertEquals(Values.clone(l), l);
    }

    @Test
    @DisplayName("Can clone lists")
    void testCloneList() {
        // Ensure the clone completes successfully
        ArrayList<Integer> a1 = new ArrayList<>(List.of(1,2,3,4,5));
        ArrayList<Integer> a2 = (ArrayList<Integer>) Values.clone(a1);
        assertEquals(a1, a2);

        // Now reassign an element and ensure only structural equality
        a1.set(1, 42);
        assertNotEquals(a1, a2);
        a2.set(1, 42);
        assertEquals(a1, a2);

        // Now append an element and ensure only structural equality.
        a1.add(99);
        assertNotEquals(a1, a2);
        a2.add(99);
        assertEquals(a1, a2);
    }

    @Test
    @DisplayName("Can clone sets")
    void testCloneSet() {
        // Ensure the clone completes successfully
        LinkedHashSet<Integer> s1 = new LinkedHashSet<>(List.of(1,2,3,4,5));
        LinkedHashSet<Integer> s2 = (LinkedHashSet<Integer>) Values.clone(s1);
        assertEquals(s1, s2);

        // Now mutate an element and ensure only structural equality
        s1.add(6);
        assertNotEquals(s1, s2);
        s2.add(6);
        assertEquals(s1, s2);
    }

    @Test
    @DisplayName("Can clone maps")
    void testCloneMap() {
        HashMap<String, Integer> m1 = new HashMap<>(Map.of(
                "A", 1,
                "B", 2,
                "C", 3));
        HashMap<String, Integer> m2 = (HashMap<String, Integer>) Values.clone(m1);
        assertEquals(m1, m2);


        // Ensure structural equality under adding elements
        m1.put("Z", 42);
        assertNotEquals(m1, m2);
        m2.put("Z", 42);
        assertEquals(m1, m2);

        // Ensure structural equality under removing elements
        m1.remove("B");
        assertNotEquals(m1, m2);
        m2.remove("B");
        assertEquals(m1, m2);

        // Ensure structural equality under reassigning values
        m1.put("C", 99);
        assertNotEquals(m1, m2);
        m2.put("C", 99);
        assertEquals(m1, m2);
    }

    @Test
    @DisplayName("Can clone nested collections")
    void testNestedCollections() {
        HashMap<String, ArrayList<Integer>> m1 = new HashMap<>(Map.of(
                "123", new ArrayList<>(List.of(1,2,3)),
                "987", new ArrayList<>(List.of(9,8,7))
        ));
        HashMap<String, ArrayList<Integer>> m2 = (HashMap<String, ArrayList<Integer>>) Values.clone(m1);
        assertEquals(m1, m2);

        // Mutate a mutable reference value and ensure no aliasing
        m1.get("123").add(4);
        assertNotEquals(m1, m2);
    }

    @Test
    @DisplayName("Cannot clone a non-P value class")
    void testInvalidCloneOfUnrelatedClass() {
        // AtomicInteger extends j.l.Number extends j.l.Object - this is a totally
        // distinct class from any P value the Java code generator emits.
        AtomicInteger i = new AtomicInteger(42);
        assertThrows(Values.UncloneableValueException.class, () -> Values.clone(i));
    }

    private class FancyLinkedHashSet<E> extends LinkedHashSet<E> {
        public FancyLinkedHashSet(List<E> e) { super(e); }
    }

    @Test
    @DisplayName("Cannot clone a subclass of a valid cloneable class")
    void testInvalidCloneOfSubclass() {
        // LinkedHashSet extends hashSet, but we expect this should fail nonetheless.
        // (Relaxing this criterion would require walking the inheritance tree via
        // reflection, and it isn't clear what the return type of the cloned value would be.)
        FancyLinkedHashSet<Integer> lh = new FancyLinkedHashSet<>(List.of(1,2,3,4,5));
        assertThrows(Values.UncloneableValueException.class, () -> Values.clone(lh));
    }
}
