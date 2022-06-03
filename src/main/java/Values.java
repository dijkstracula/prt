import java.util.*;

/**
 * Holds some routines for value cloning and comparisons.
 */
public class Values {

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
            cloned.add(clone(val));
        }
        return cloned;
    }

    private static HashSet<Object> cloneSet(HashSet<?> s)
    {
        HashSet<Object> cloned = new HashSet<>();
        for (Object val : s) {
            cloned.add(clone(val));
        }
        return cloned;
    }

    private static HashMap<Object, Object> cloneMap(HashMap<?, ?> m)
    {
        HashMap<Object, Object> cloned = new HashMap<>();
        for (Map.Entry e : m.entrySet()) {
            Object k = clone(e.getKey());
            Object v = clone(e.getValue());
            cloned.put(k, v);
        }
        return cloned;
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
    public static Object clone(Object o) {
        if (o == null) {
            return null;
        }

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
        if (clazz == HashSet.class)
            return cloneSet((HashSet<?>) o);

        throw new UncloneableValueException(clazz);
    }

    public static <T extends Comparable<T>> int compare(T o1, T o2) {
        // Just to keep things well-defined, treat null as a minimal value.
        if (o1 == null) {
            return o2 == null ? 0 : -1;
        }
        if (o2 == null) {
            return o1 == null ? 0 : 1;
        }

        // String.compareTo(String) doesn't return a value on [-1, 1] so clamp out-of-range values.
        int ret = o1.compareTo(o2);
        if (ret > 1) ret = 1;
        if (ret < -1) ret = -1;

        return ret;
    }

    public static boolean equals(Object o1, Object o2) {
        return Objects.equals(o1, o2);
    }
}
