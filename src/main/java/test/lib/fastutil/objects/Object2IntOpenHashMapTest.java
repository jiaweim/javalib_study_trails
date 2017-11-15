package test.lib.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class Object2IntOpenHashMapTest
{

    @Test
    void test()
    {
        Object2IntOpenHashMap<String> map = new Object2IntOpenHashMap<>(Hash.DEFAULT_INITIAL_SIZE, Hash.DEFAULT_LOAD_FACTOR);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);


    }

    private static java.util.Random r = new java.util.Random(0);

    private static Object genKey()
    {
        return Integer.toBinaryString(r.nextInt());
    }

    private static int genValue()
    {
        return r.nextInt();
    }

    private static boolean valEquals(Object o1, Object o2)
    {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    // validate the correctness of Object2IntOpenhashMap.
    @SuppressWarnings("unchecked")
    protected static void test(int n, float f) throws IOException, ClassNotFoundException
    {
        Object2IntOpenHashMap m = new Object2IntOpenHashMap(Hash.DEFAULT_INITIAL_SIZE, f);
        Map t = new java.util.HashMap();
        /* First of all, we fill t with random data. */
        for (int i = 0; i < n; i++)
            t.put((genKey()), (genValue()));

        /* Now we add to m the same data */
        m.putAll(t);
        assertTrue(m.equals(t));
        assertTrue(t.equals(m));

        /* Now we check that m actually holds that data. */
        for (Object o1 : t.entrySet()) {
            Map.Entry e = (Map.Entry) o1;
            assertTrue(valEquals(e.getValue(), m.getInt(e.getKey())));
        }

		/* Now we check that m actually holds that data, but iterating on m. */
        for (Object o1 : m.entrySet()) {
            Map.Entry e = (Map.Entry) o1;
            assertTrue(valEquals(e.getValue(), t.get(e.getKey())));
        }

		/* Now we check that m actually holds the same keys. */
        for (Object o : t.keySet()) {
            assertTrue(m.containsKey(o));
            assertTrue(m.keySet().contains(o));
        }

		/* Now we check that m actually holds the same keys, but iterating on m. */
        for (Object o : m.keySet()) {
            assertTrue(t.containsKey(o));
            assertTrue(t.keySet().contains(o));
        }

		/* Now we check that m actually hold the same values. */
        for (Object o : t.values()) {
            assertTrue(m.containsValue(o));
            assertTrue(m.values().contains(o));
        }

		/* Now we check that m actually hold the same values, but iterating on m. */
        for (Integer o : m.values()) {
            assertTrue(t.containsValue(o));
            assertTrue(t.values().contains(o));
        }

		/*
         * Now we check that inquiries about random data give the same answer in m and t. For m we
		 * use the polymorphic method.
		 */
        for (int i = 0; i < n; i++) {
            Object T = genKey();
            assertFalse(m.containsKey((T)) != t.containsKey((T)));
            assertFalse((m.getInt(T) != 0) != ((t.get(T) == null ? 0 : ((Integer) (t.get(T))))
                    != 0) || t.get(T) != null && !(Integer.valueOf(m.getInt(T))).equals(t.get(T)));
        }

		/*
		 * Again, we check that inquiries about random data give the same answer in m and t, but for
		 * m we use the standard method.
		 */
        for (int i = 0; i < n; i++) {
            Object T = genKey();
            assertTrue(valEquals(m.get((T)), t.get((T))));
        }

		/* Now we put and remove random data in m and t, checking that the result is the same. */
        for (int i = 0; i < 20 * n; i++) {
            Object T = genKey();
            int U = genValue();
            assertTrue(valEquals(m.put(T, Integer.valueOf(U)), t.put(T, U)));
            T = genKey();
            assertTrue(valEquals(m.remove(T), t.remove(T)));
        }
        assertTrue(m.equals(t));
        assertTrue(t.equals(m));

		/* Now we check that m actually holds the same data. */
        for (Object o1 : t.entrySet()) {
            Map.Entry e = (Map.Entry) o1;
            assertTrue(valEquals(e.getValue(), m.get(e.getKey())));
        }

		/* Now we check that m actually holds that data, but iterating on m. */
        for (Object o1 : m.entrySet()) {
            Map.Entry e = (Map.Entry) o1;
            assertTrue(valEquals(e.getValue(), t.get(e.getKey())));
        }

		/* Now we check that m actually holds the same keys. */
        for (Object o : t.keySet()) {
            assertTrue(m.containsKey(o));
            assertTrue(m.keySet().contains(o));
        }

		/* Now we check that m actually holds the same keys, but iterating on m. */
        for (Object o : m.keySet()) {
            assertTrue(t.containsKey(o));
            assertTrue(t.keySet().contains(o));
        }

		/* Now we check that m actually hold the same values. */
        for (Object o : t.values()) {
            assertTrue(m.containsValue(o));
            assertTrue(m.values().contains(o));
        }

		/* Now we check that m actually hold the same values, but iterating on m. */
        for (Integer o : m.values()) {
            assertTrue(t.containsValue(o));
            assertTrue(t.values().contains(o));
        }
        int h = m.hashCode();

		/* Now we save and read m. */
        java.io.File ff = new java.io.File("it.unimi.dsi.fastutil.test");
        java.io.OutputStream os = new java.io.FileOutputStream(ff);
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(os);
        oos.writeObject(m);
        oos.close();
        java.io.InputStream is = new java.io.FileInputStream(ff);
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(is);
        m = (Object2IntOpenHashMap) ois.readObject();
        ois.close();
        ff.delete();
        assertTrue(m.hashCode() == h);

		/* Now we check that m actually holds that data. */
        for (Object o : t.keySet()) {
            assertTrue(valEquals(m.get(o), t.get(o)));
        }

		/* Now we put and remove random data in m and t, checking that the result is the same. */
        for (int i = 0; i < 20 * n; i++) {
            Object T = genKey();
            int U = genValue();
            assertTrue(valEquals(m.put((T), (Integer.valueOf(U))), t.put((T), (U))));
            T = genKey();
            assertTrue(valEquals(m.remove((T)), t.remove((T))));
        }
        assertTrue(m.equals(t));
        assertTrue(t.equals(m));
		/* Now we take out of m everything, and check that it is empty. */
        for (Object o : t.keySet()) m.remove(o);
        assertTrue(m.isEmpty());
    }

    @Test
    void test1() throws IOException, ClassNotFoundException
    {
        test(1, Hash.DEFAULT_LOAD_FACTOR);
        test(1, Hash.FAST_LOAD_FACTOR);
        test(1, Hash.VERY_FAST_LOAD_FACTOR);
    }

    @Test
    void test10() throws IOException, ClassNotFoundException
    {
        test(10, Hash.DEFAULT_LOAD_FACTOR);
        test(10, Hash.FAST_LOAD_FACTOR);
        test(10, Hash.VERY_FAST_LOAD_FACTOR);
    }

    @Test
    void test100() throws IOException, ClassNotFoundException
    {
        test(100, Hash.DEFAULT_LOAD_FACTOR);
        test(100, Hash.FAST_LOAD_FACTOR);
        test(100, Hash.VERY_FAST_LOAD_FACTOR);
    }

    @Test(enabled = false, description = "Too long")
    void test1000() throws IOException, ClassNotFoundException
    {
        test(1000, Hash.DEFAULT_LOAD_FACTOR);
        test(1000, Hash.FAST_LOAD_FACTOR);
        test(1000, Hash.VERY_FAST_LOAD_FACTOR);
    }


}

