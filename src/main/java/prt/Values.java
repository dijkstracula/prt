package prt;

import events.PObserveEvent;

import java.util.*;

/**
 * Holds some routines for value cloning and comparisons.
 */
public class Values {
    public interface PTuple<P extends PTuple<?>> {
        /**
         * Performs a deep copy of a P tuple by
         * (The performance difference between a hand-rolled deep copy and using serializers
         * appears to be significant[1], so doing the former seems to be a good idea.)
         * [1]: <a href="https://www.infoworld.com/article/2077578/java-tip-76--an-alternative-to-the-deep-copy-technique.html">...</a>
         *
         * @return a structurally-equivalent version of `this` but such that mutations of
         * one object are not visible within the other.
         */
        P deepClone();

        /**
         * Performs a deep equality check against another Object.
         * @param o2 The other object.
         * @return If this and o2 are object of the same class, and their fields are deeply equal to each other's.
         */
        boolean deepEquals(P o2);
    }

    public static class UncloneableValueException extends RuntimeException {
        public UncloneableValueException(Class<?> c) {
            super(String.format("No clone operation for class " + c.getName()));
        }
    }

    /* Note: In theory, the Java compiler should be able to elide many of
     * the boxed primitive type calls (since Java knows it can unbox it and copy the
     * POD value to another variable), but for nested data structure cloning
     * they are included.
     */

    private static Boolean cloneBoolean(Boolean b) {
        return b; //already immutable!  No cloning necessary.
    }

    private static Integer cloneInteger(Integer i) {
        return i; //already immutable!  No cloning necessary.
    }

    private static Long cloneLong(Long l) {
        // NB: Actor IDs are stored as Longs.
        return l; //already immutable!  No cloning necessary.
    }

    private static Float cloneFloat(Float f) {
        return f; //already immutable!  No cloning necessary.
    }

    private static String cloneString(String s) {
        return s; //already immutable!  No cloning necessary.
    }

    private static ArrayList<Object> cloneList(ArrayList<?> a) {
        ArrayList<Object> cloned = new ArrayList<>();
        cloned.ensureCapacity(a.size());
        for (Object val : a) {
            cloned.add(deepClone(val));
        }
        return cloned;
    }

    private static LinkedHashSet<Object> cloneSet(LinkedHashSet<?> s)
    {
        LinkedHashSet<Object> cloned = new LinkedHashSet<>();
        for (Object val : s) {
            cloned.add(deepClone(val));
        }
        return cloned;
    }

    private static HashMap<Object, Object> cloneMap(HashMap<?, ?> m)
    {
        HashMap<Object, Object> cloned = new HashMap<>();
        for (Map.Entry<?,?> e : m.entrySet()) {
            Object k = deepClone(e.getKey());
            Object v = deepClone(e.getValue());
            cloned.put(k, v);
        }
        return cloned;
    }

    private static PTuple<?> cloneTuple(PTuple<?> tuple)
    {
        return tuple.deepClone();
    }

    /**
     * Performs a deep copy of a P program value by dispatching on the Object type.
     * (The performance difference between a hand-rolled deep copy and using serializers
     * appears to be significant[1], so doing the former seems to be a good idea.)
     * [1]: https://www.infoworld.com/article/2077578/java-tip-76--an-alternative-to-the-deep-copy-technique.html
     *
     * @param o the value to clone
     * @return a structurally-equivalent version of `o` but such that mutations of
     * one object are not visible within the other.
     */
    public static Object deepClone(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof PTuple<?>)
            return cloneTuple((PTuple<?>) o);

        Class<?> clazz = o.getClass();
        if (clazz == Boolean.class)
            return cloneBoolean((Boolean)o);
        if (clazz == Integer.class)
            return cloneInteger((Integer)o);
        if (clazz == Long.class)
            return cloneLong((Long)o);
        if (clazz == Float.class)
            return cloneFloat((Float)o);
        if (clazz == String.class)
            return cloneString((String)o);
        if (clazz == ArrayList.class)
            return cloneList((ArrayList<?>) o);
        if (clazz == HashMap.class)
            return cloneMap((HashMap<?, ?>) o);
        if (clazz == LinkedHashSet.class)
            return cloneSet((LinkedHashSet<?>) o);


        throw new UncloneableValueException(clazz);
    }

    public static <T extends Comparable<T>> int compare(T o1, T o2) {
        // Just to keep things well-defined, treat null as a minimal value.
        if (o1 == null) {
            return o2 == null ? 0 : -1;
        }
        if (o2 == null) {
            return 1;
        }

        // String.compareTo(String) doesn't return a value on [-1, 1] so clamp out-of-range values.
        int ret = o1.compareTo(o2);
        if (ret > 1) ret = 1;
        if (ret < -1) ret = -1;

        return ret;
    }

    private static <T1, T2> boolean deepLinkedHashSetEquals(LinkedHashSet<T1> a1, LinkedHashSet<T2> a2) {
        // A direct equality call matches the behaviour of Plang.CSharpRuntime.Values.PrtSet.Equals .
        return a1.equals(a2);
    }

    private static <T1, T2> boolean deepArrayEquals(ArrayList<T1> a1, ArrayList<T2> a2) {
        if (a1.size() != a2.size()) {
            return false;
        }
        for (int i = 0; i < a1.size(); i++) {
            T1 v1 = a1.get(i);
            T2 v2 = a2.get(i);
            if (v1.getClass() != v2.getClass()) {
                return false;
            }
            if (!deepEquals(v1, v2)) {
                return false;
            }
        }
        return true;
    }


    private static <K1, V1, K2, V2> boolean deepHashMapEquals(HashMap<K1, V1> m1, HashMap<K2, V2> m2) {
        if (!m1.keySet().equals(m2.keySet())) {
            return false;
        }
        for (K1 k : m1.keySet()) {
            V1 v1 = m1.get(k);
            V2 v2 = m2.get(k);
            if (!Values.deepEquals(v1, v2)) {
                return false;
            }
        }
        return true;
    }

    private static <P1 extends PTuple<P1>, P2 extends PTuple<P2>> boolean deepTupleEquals(P1 t1, P2 t2) {
        if (t1.getClass() != t2.getClass()) {
            return false;
        }
        return t1.deepEquals((P1)t2);
    }


    public static boolean deepEquals(Object o1, Object o2) {
        // Only a null is equal to a null, to keep things well-defined.
        if (o1 == null) {
            return o2 == null;
        }
        if (o2 == null) {
            return false;
        }

        try {
            // For PTuples, defer to their `deepEquals()` method (which may recursively call
            // back into `Values.deepEquals()`.
            if (o1 instanceof PTuple<?> && o2 instanceof PTuple<?>) {
                return deepTupleEquals((PTuple) o1, (PTuple) o2);
            }

            if (o1 instanceof PObserveEvent && o2 instanceof PObserveEvent) {
                PObserveEvent p1 = (PObserveEvent) o1;
                PObserveEvent p2 = (PObserveEvent) o2;
                return p1.ts() == p2.ts() &&
                        Values.deepEquals(p1.pEvent(), p2.pEvent());
            }

            // Otherwise, dispatch on the classes.
            Class<?> c1 = o1.getClass();
            Class<?> c2 = o2.getClass();
            if (c1 == Boolean.class && c2 == Boolean.class)
                return compare((Boolean) o1, (Boolean) o2) == 0;
            if (c1 == Integer.class && c2 == Integer.class)
                return compare((Integer) o1, (Integer) o2) == 0;
            if (c1 == Long.class && c2 == Long.class)
                return compare((Long) o1, (Long) o2) == 0;
            if (c1 == Float.class && c2 == Float.class)
                return compare((Float) o1, (Float) o2) == 0;
            if (c1 == String.class && c2 == String.class)
                return compare((String) o1, (String) o2) == 0;
            if (c1 == ArrayList.class && c2 == ArrayList.class)
                return deepArrayEquals((ArrayList<?>) o1, (ArrayList<?>) o2);
            if (c1 == HashMap.class && c2 == HashMap.class)
                return deepHashMapEquals((HashMap<?, ?>) o1, (HashMap<?, ?>) o2);
            if (c1 == LinkedHashSet.class && c2 == LinkedHashSet.class)
                return deepLinkedHashSetEquals((LinkedHashSet<?>) o1, (LinkedHashSet<?>) o2);
        } catch (ClassCastException e) {
            // The C# P runtime is pretty permissive about comparing different types (no runtime exception,
            // they just evaluate to false) so we do the same here in case we are passed incomparable types.
            return false;
        }
        return false;
    }

    // A helper that walks a LinkedHashSet's iterator to get the `i`th value in the set.  This is
    // implemented here as J.u.LinkedHashSet has no equivalent of C# HashSet::elementAt() method.
    public static <T> T setElementAt(LinkedHashSet<T> s, int i) throws NoSuchElementException
    {
        if (i < 0) throw new NoSuchElementException();
        Iterator<T> it = s.iterator();
        T ret = it.next();

        while (i > 0) {
            ret = it.next();
            i--;
        }

        return ret;
    }
}
