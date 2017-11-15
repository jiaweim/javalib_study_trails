package test.lib.fastutil.ints;

import it.unimi.dsi.fastutil.BigArrays;
import it.unimi.dsi.fastutil.ints.*;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Iterator;

import static org.testng.Assert.*;

@SuppressWarnings("rawtypes")
public class IntBigArrayBigListTest
{

    @Test
    public void testRemoveAllModifiesCollection()
    {
        IntBigList list = new IntBigArrayBigList();
        assertFalse(list.removeAll(Collections.emptySet()));
        assertEquals(IntBigLists.EMPTY_BIG_LIST, list);
    }

    @SuppressWarnings("boxing")
    @Test
    public void testRemoveAllSkipSegment()
    {
        IntBigList list = new IntBigArrayBigList();
        for (long i = 0; i < BigArrays.SEGMENT_SIZE + 10; i++) list.add((int) (i % 2));
        assertTrue(list.removeAll(IntSets.singleton(1)));
        assertEquals(BigArrays.SEGMENT_SIZE / 2 + 5, list.size64());
        for (long i = 0; i < BigArrays.SEGMENT_SIZE / 2 + 5; i++) assertEquals(0, list.getInt(i));

        list = new IntBigArrayBigList();
        for (long i = 0; i < BigArrays.SEGMENT_SIZE + 10; i++) list.add((int) (i % 2));
        assertTrue(list.removeAll(Collections.singleton(1)));
        assertEquals(BigArrays.SEGMENT_SIZE / 2 + 5, list.size64());
        for (long i = 0; i < BigArrays.SEGMENT_SIZE / 2 + 5; i++) assertEquals(0, list.getInt(i));
    }


    @Test
    public void testListIteratorTooLow()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> new IntBigArrayBigList().listIterator(-1L));

    }

    @Test
    public void testListIteratorTooHigh()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> new IntBigArrayBigList().listIterator(1L));

    }

    @Test
    public void testAddWithIterator()
    {
        IntBigList list = new IntBigArrayBigList();
        list.iterator().add(1);
        assertEquals(IntBigLists.singleton(1), list);
    }

    @Test
    public void testRemoveAll()
    {
        IntBigArrayBigList l = IntBigArrayBigList.wrap(new int[][]{{0, 1, 2}});
        l.removeAll(IntSets.singleton(1));
        assertEquals(IntBigArrayBigList.wrap(new int[][]{{0, 2}}), l);

        l = IntBigArrayBigList.wrap(new int[][]{{0, 1, 1, 2}});
        l.removeAll(Collections.singleton(Integer.valueOf(1)));
        assertEquals(IntBigArrayBigList.wrap(new int[][]{{0, 2}}), l);
    }

    private static java.util.Random r = new java.util.Random(0);

    private static int genKey()
    {
        return r.nextInt();
    }

    private static Object[] k, nk;

    private static int kt[];

    private static int nkt[];

    @SuppressWarnings("unchecked")
    protected static void testLists(IntBigList m, IntBigList t, int n, int level)
    {
        Exception mThrowsOutOfBounds, tThrowsOutOfBounds;
        Object rt = null;
        int rm = (0);
        if (level > 4) return;
        /* Now we check that both sets agree on random keys. For m we use the polymorphic method. */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (n * 2);
            int T = genKey();
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.set(p, T);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.set(p, (Integer.valueOf(T)));
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }

            if (mThrowsOutOfBounds == null)
                p = r.nextInt() % (n * 2);
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.getInt(p);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.get(p);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds == null) ==
                    (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(t.get(p).equals((Integer.valueOf(m.getInt(p)))));
        }
        /* Now we check that both sets agree on random keys. For m we use the standard method. */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (n * 2);
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.get(p);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.get(p);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds == null) ==
                    (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(t.get(p).equals(m.get(p)));
        }
        /* Now we check that m and t are equal. */
        if (!m.equals(t) || !t.equals(m)) System.err.println("m: " + m + " t: " + t);
        assertTrue(m.equals(t));
        assertTrue(t.equals(m));
        /* Now we check that m actually holds that data. */
        for (Iterator i = t.iterator(); i.hasNext(); ) {
            assertTrue(m
                    .contains(i.next()));
        }
		/* Now we check that m actually holds that data, but iterating on m. */
        for (Iterator i = m.listIterator(); i.hasNext(); ) {
            assertTrue(t
                    .contains(i.next()));
        }
		/*
		 * Now we check that inquiries about random data give the same answer in m and t. For m we
		 * use the polymorphic method.
		 */
        for (int i = 0; i < n; i++) {
            int T = genKey();
            assertTrue(m
                    .contains(T) == t.contains((Integer.valueOf(T))));
        }
		/*
		 * Again, we check that inquiries about random data give the same answer in m and t, but for
		 * m we use the standard method.
		 */
        for (int i = 0; i < n; i++) {
            int T = genKey();
            assertTrue(m
                    .contains((Integer.valueOf(T))) == t.contains((Integer.valueOf(T))));
        }
		/* Now we add and remove random data in m and t, checking that the result is the same. */
        for (int i = 0; i < 2 * n; i++) {
            int T = genKey();
            try {
                m.add(T);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.add((Integer.valueOf(T)));
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            T = genKey();
            int p = r.nextInt() % (2 * n + 1);
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.add(p, T);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.add(p, (Integer.valueOf(T)));
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds == null)
                    == (tThrowsOutOfBounds == null));
            p = r.nextInt() % (2 * n + 1);
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                rm = m.removeInt(p);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                rt = t.remove(p);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds == null) ==
                    (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(
                        rt.equals((Integer.valueOf(rm))));
        }
        assertTrue(m.equals(t));
        assertTrue(t.equals(m));
		/*
		 * Now we add random data in m and t using addAll on a collection, checking that the result
		 * is the same.
		 */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (2 * n + 1);
            java.util.Collection m1 = new java.util.ArrayList();
            int s = r.nextInt(n / 2 + 1);
            for (int j = 0; j < s; j++)
                m1.add((Integer.valueOf(genKey())));
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.addAll(p, m1);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.addAll(p, m1);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds ==
                    null) == (tThrowsOutOfBounds == null));
            assertTrue(m.equals(t));
            assertTrue(t.equals(m));
        }
        if (m.size64() > n) {
            m.size(n);
            while (t.size() != n)
                t.remove(t.size() - 1);
        }
		/*
		 * Now we add random data in m and t using addAll on a type-specific collection, checking
		 * that the result is the same.
		 */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (2 * n + 1);
            IntCollection m1 = new IntBigArrayBigList();
            java.util.Collection t1 = new java.util.ArrayList();
            int s = r.nextInt(n / 2 + 1);
            for (int j = 0; j < s; j++) {
                int x = genKey();
                m1.add(x);
                t1.add((Integer.valueOf(x)));
            }
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.addAll(p, m1);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.addAll(p, t1);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue(
                    (mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
            assertTrue(m.equals(t));
            assertTrue(t.equals(m));
        }
        if (m.size64() > n) {
            m.size(n);
            while (t.size() != n)
                t.remove(t.size() - 1);
        }
		/*
		 * Now we add random data in m and t using addAll on a list, checking that the result is the
		 * same.
		 */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (2 * n + 1);
            IntBigList m1 = new IntBigArrayBigList();
            java.util.Collection t1 = new java.util.ArrayList();
            int s = r.nextInt(n / 2 + 1);
            for (int j = 0; j < s; j++) {
                int x = genKey();
                m1.add(x);
                t1.add((Integer.valueOf(x)));
            }
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.addAll(p, m1);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.addAll(p, t1);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds
                    == null) == (tThrowsOutOfBounds == null));
            assertTrue(m.equals(t));
            assertTrue(t.equals(m));
        }
		/* Now we check that both sets agree on random keys. For m we use the standard method. */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (n * 2);
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.get(p);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.get(p);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds == null) ==
                    (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(t.get(p).equals(m.get(p)));
        }
		/* Now we inquiry about the content with indexOf()/lastIndexOf(). */
        for (int i = 0; i < 10 * n; i++) {
            int T = genKey();
            assertTrue(m.indexOf((Integer.valueOf(T))) == t.indexOf((Integer
                    .valueOf(T))));
            assertTrue(m.lastIndexOf((Integer.valueOf
                    (T))) == t.lastIndexOf((Integer.valueOf(T))));
            assertTrue(m.indexOf(T) == t.indexOf((Integer.valueOf(T))));
            assertTrue(m.lastIndexOf(T) == t.lastIndexOf((Integer
                    .valueOf(T))));
        }
		/* Now we check cloning. */
        if (level == 0) {
            assertTrue(m.equals(((IntBigArrayBigList) m).clone()));
            assertTrue(((IntBigArrayBigList) m).clone().equals(m));
        }
		/* Now we play with constructors. */
        assertTrue(m.equals(new
                IntBigArrayBigList((IntCollection) m)));
        assertTrue((new
                IntBigArrayBigList((IntCollection) m)).equals(m));
        assertTrue(m.equals(new
                IntBigArrayBigList(m)));
        assertTrue((new IntBigArrayBigList(m)
        ).equals(m));
        assertTrue(m.equals(new
                IntBigArrayBigList(m.listIterator())));
        assertTrue((new IntBigArrayBigList(m
                .listIterator())).equals(m));
        assertTrue(m.equals(new
                IntBigArrayBigList(m.iterator())));
        assertTrue((new
                IntBigArrayBigList(m.iterator())).equals(m));
        int h = m.hashCode();
		/* Now we save and read m. */
        IntBigList m2 = null;
        try {
            java.io.File ff = new java.io.File("it.unimi.dsi.fastutil.test");
            java.io.OutputStream os = new java.io.FileOutputStream(ff);
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(os);
            oos.writeObject(m);
            oos.close();
            java.io.InputStream is = new java.io.FileInputStream(ff);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(is);
            m2 = (IntBigList) ois.readObject();
            ois.close();
            ff.delete();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        assertTrue(m2.hashCode() == h);
		/* Now we check that m2 actually holds that data. */
        assertTrue(m2.equals(t));
        assertTrue(t.equals(m2));
		/* Now we take out of m everything, and check that it is empty. */
        for (Iterator i = t.iterator(); i.hasNext(); )
            m2.remove(i.next());
        assertTrue(m2.isEmpty());
		/* Now we play with iterators. */
        {
            IntBigListIterator i;
            IntBigListIterator j;
            i = m.listIterator();
            j = t.listIterator();
            for (int k = 0; k < 2 * n; k++) {
                assertTrue(i.hasNext() == j.hasNext());
                assertTrue(i.hasPrevious() == j.hasPrevious());
                if (r.nextFloat() < .8 && i.hasNext()) {
                    assertTrue(i.next().equals(j.next()));
                    if (r.nextFloat() < 0.2) {
                        i.remove();
                        j.remove();
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.set(T);
                        j.set((Integer.valueOf(T)));
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.add(T);
                        j.add((Integer.valueOf(T)));
                    }
                } else if (r.nextFloat() < .2 && i.hasPrevious()) {
                    assertTrue(i.previous().equals(j.previous()));
                    if (r.nextFloat() < 0.2) {
                        i.remove();
                        j.remove();
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.set(T);
                        j.set((Integer.valueOf(T)));
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.add(T);
                        j.add((Integer.valueOf(T)));
                    }
                }
                assertTrue(i.nextIndex() == j.nextIndex());
                assertTrue(i.previousIndex() == j
                        .previousIndex());
            }
        }
        {
            Object I, J;
            int from = r.nextInt(m.size() + 1);
            IntBigListIterator i;
            IntBigListIterator j;
            i = m.listIterator(from);
            j = t.listIterator(from);
            for (int k = 0; k < 2 * n; k++) {
                assertTrue(i.hasNext() == j.hasNext());
                assertTrue(i.hasPrevious() == j.hasPrevious());
                if (r.nextFloat() < .8 && i.hasNext()) {
                    I = i.next();
                    J = j.next();
                    assertTrue(I.equals(J));
                    // System.err.println("Done next " + I + " " + J + "  " + badPrevious);
                    if (r.nextFloat() < 0.2) {
                        // System.err.println("Removing in next");
                        i.remove();
                        j.remove();
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.set(T);
                        j.set((Integer.valueOf(T)));
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.add(T);
                        j.add((Integer.valueOf(T)));
                    }
                } else if (r.nextFloat() < .2 && i.hasPrevious()) {
                    I = i.previous();
                    J = j.previous();
                    assertTrue(I.equals(J));
                    if (r.nextFloat() < 0.2) {
                        // System.err.println("Removing in prev");
                        i.remove();
                        j.remove();
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.set(T);
                        j.set((Integer.valueOf(T)));
                    } else if (r.nextFloat() < 0.2) {
                        int T = genKey();
                        i.add(T);
                        j.add((Integer.valueOf(T)));
                    }
                }
            }
        }
		/* Now we check that m actually holds that data. */
        assertTrue(m.equals(t));
        assertTrue(t.equals(m));
		/* Now we select a pair of keys and create a subset. */
        if (!m.isEmpty()) {
            int start = r.nextInt(m.size());
            int end = start + r.nextInt(m.size() - start);
            // System.err.println("Checking subList from " + start + " to " + end + " (level=" +
            // (level+1) + ")..." );
            testLists(m.subList(start, end), t.subList(start, end), n, level + 1);
            assertTrue(m.equals(t));
            assertTrue(t.equals(m));
        }
        m.clear();
        t.clear();
        assertTrue(m.isEmpty());
    }

    protected static void test(int n)
    {
        IntBigArrayBigList m = new IntBigArrayBigList();
        IntBigList t = IntBigLists.asBigList(new IntArrayList());
        k = new Object[n];
        nk = new Object[n];
        kt = new int[n];
        nkt = new int[n];
        for (int i = 0; i < n; i++) {
            k[i] = new Integer(kt[i] = genKey());
            nk[i] = new Integer(nkt[i] = genKey());
        }
		/* We add pairs to t. */
        for (int i = 0; i < n; i++)
            t.add((Integer) k[i]);
		/* We add to m the same data */
        m.addAll(t);
        testLists(m, t, n, 0);
    }

    @Test void test1()
    {
        test(1);
    }

    @Test void test10()
    {
        test(10);
    }

    @Test void test100()
    {
        test(100);
    }

    @Test(enabled = false)
    void test1000()
    {
        test(1000);
    }
}
