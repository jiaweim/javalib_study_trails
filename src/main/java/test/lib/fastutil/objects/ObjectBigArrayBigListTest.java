package test.lib.fastutil.objects;

import it.unimi.dsi.fastutil.BigArrays;
import it.unimi.dsi.fastutil.objects.*;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


@SuppressWarnings("rawtypes")
public class ObjectBigArrayBigListTest {

    @Test
    void testRemoveAllModifiesCollection() {
        ObjectBigList<Integer> list = new ObjectBigArrayBigList<>();
        assertFalse(list.removeAll(Collections.emptySet()));
        assertEquals(ObjectBigLists.EMPTY_BIG_LIST, list);
    }

    @SuppressWarnings("boxing")
    @Test
    void testRemoveAllSkipSegment() {
        ObjectBigList<Integer> list = new ObjectBigArrayBigList<Integer>();
        for (long i = 0; i < BigArrays.SEGMENT_SIZE + 10; i++) list.add(Integer.valueOf((int) (i % 2)));
        assertTrue(list.removeAll(ObjectSets.singleton(1)));
        assertEquals(BigArrays.SEGMENT_SIZE / 2 + 5, list.size64());
        for (long i = 0; i < BigArrays.SEGMENT_SIZE / 2 + 5; i++) assertEquals(Integer.valueOf(0), list.get(i));
    }

    private static java.util.Random r = new java.util.Random(0);

    private static int genKey() {
        return r.nextInt();
    }

    private static Object[] k, nk;

    private static Object kt[];

    private static Object nkt[];

    @SuppressWarnings({"boxing"})
    protected static void testLists(ObjectBigList m, ObjectBigList t, int n, int level) {
        Exception mThrowsOutOfBounds, tThrowsOutOfBounds;
        Object rt = null;
        Object rm = (null);
        if (level > 4) return;
        /* Now we check that both sets agree on random keys. For m we use the polymorphic method. */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (n * 2);
            Object T = genKey();
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                m.set(p, T);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.set(p, (T));
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue((mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(t.get(p).equals((m.get(p))));
            p = r.nextInt() % (n * 2);
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
            assertTrue((mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(t.get(p).equals((m.get(p))));
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
            assertTrue(
                    (mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(t.get(p)
                        .equals(m.get(p)));
        }
        /* Now we check that m and t are equal. */
        if (!m.equals(t) || !t.equals(m)) System.err.println("m: " + m + " t: " + t);
        assertTrue(m.equals(t));
        assertTrue(t.equals(m));
        /* Now we check that m actually holds that data. */
        for (Object aT1 : t) {
            assertTrue(m
                    .contains(aT1));
        }
        /* Now we check that m actually holds that data, but iterating on m. */
        for (Object aM : m) {
            assertTrue(t
                    .contains(aM));
        }
		/*
		 * Now we check that inquiries about random data give the same answer in m and t. For m we
		 * use the polymorphic method.
		 */
        for (int i = 0; i < n; i++) {
            Object T = genKey();
            assertTrue(m
                    .contains(T) == t.contains((T)));
        }
		/*
		 * Again, we check that inquiries about random data give the same answer in m and t, but for
		 * m we use the standard method.
		 */
        for (int i = 0; i < n; i++) {
            Object T = genKey();
            assertTrue(m
                    .contains((T)) == t.contains((T)));
        }
		/* Now we add and remove random data in m and t, checking that the result is the same. */
        for (int i = 0; i < 2 * n; i++) {
            Object T = genKey();
            try {
                m.add(T);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                t.add((T));
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
                t.add(p, (T));
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue(
                    (mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
            p = r.nextInt() % (2 * n + 1);
            mThrowsOutOfBounds = tThrowsOutOfBounds = null;
            try {
                rm = m.remove(p);
            } catch (IndexOutOfBoundsException e) {
                mThrowsOutOfBounds = e;
            }
            try {
                rt = t.remove(p);
            } catch (IndexOutOfBoundsException e) {
                tThrowsOutOfBounds = e;
            }
            assertTrue(
                    (mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
            if (mThrowsOutOfBounds == null)
                assertTrue(
                        rt.equals((rm)));
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
                m1.add((genKey()));
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
		 * Now we add random data in m and t using addAll on a type-specific collection, checking
		 * that the result is the same.
		 */
        for (int i = 0; i < n; i++) {
            int p = r.nextInt() % (2 * n + 1);
            ObjectCollection m1 = new ObjectBigArrayBigList();
            java.util.Collection t1 = new java.util.ArrayList();
            int s = r.nextInt(n / 2 + 1);
            for (int j = 0; j < s; j++) {
                Object x = genKey();
                m1.add(x);
                t1.add((x));
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
            assertTrue((mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
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
            ObjectBigList m1 = new ObjectBigArrayBigList();
            java.util.Collection t1 = new java.util.ArrayList();
            int s = r.nextInt(n / 2 + 1);
            for (int j = 0; j < s; j++) {
                Object x = genKey();
                m1.add(x);
                t1.add((x));
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
            assertTrue(
                    (mThrowsOutOfBounds == null) == (tThrowsOutOfBounds == null));
        }
		/* Now we inquiry about the content with indexOf()/lastIndexOf(). */
        for (int i = 0; i < 10 * n; i++) {
            Object T = genKey();
            assertTrue(m.indexOf((T)) == t.indexOf((T)));
            assertTrue(m.lastIndexOf((T)) == t.lastIndexOf((T)));
            assertTrue(m.indexOf(T) == t.indexOf((T)));
            assertTrue(m.lastIndexOf(T) == t.lastIndexOf((T)));
        }
		/* Now we check cloning. */
        if (level == 0) {
            assertTrue(m.equals(((ObjectBigArrayBigList) m)
                    .clone()));
            assertTrue(((ObjectBigArrayBigList) m).clone()
                    .equals(m));
        }
		/* Now we play with constructors. */
        assertTrue(m.equals(new ObjectBigArrayBigList((ObjectCollection) m)));
        assertTrue((new ObjectBigArrayBigList((ObjectCollection) m)).equals(m));
        assertTrue(m.equals(new ObjectBigArrayBigList(m)));
        assertTrue((new ObjectBigArrayBigList(m)).equals(m));
        assertTrue(m.equals(new ObjectBigArrayBigList(m.listIterator())));
        assertTrue((new ObjectBigArrayBigList(m.listIterator())).equals(m));
        assertTrue(m.equals(new ObjectBigArrayBigList(m.iterator())));
        assertTrue((new ObjectBigArrayBigList(m.iterator())).equals(m));
        int h = m.hashCode();
		/* Now we save and read m. */
        ObjectBigList m2 = null;
        try {
            java.io.File ff = new java.io.File("it.unimi.dsi.fastutil.test");
            java.io.OutputStream os = new java.io.FileOutputStream(ff);
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(os);
            oos.writeObject(m);
            oos.close();
            java.io.InputStream is = new java.io.FileInputStream(ff);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(is);
            m2 = (ObjectBigList) ois.readObject();
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
        for (Object aT : t) m2.remove(aT);
        assertTrue(m2.isEmpty());
		/* Now we play with iterators. */
        {
            ObjectBigListIterator i;
            ObjectBigListIterator j;
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
                        Object T = genKey();
                        i.set(T);
                        j.set((T));
                    } else if (r.nextFloat() < 0.2) {
                        Object T = genKey();
                        i.add(T);
                        j.add((T));
                    }
                } else if (r.nextFloat() < .2 && i.hasPrevious()) {
                    assertTrue(i.previous().equals(j.previous()));
                    if (r.nextFloat() < 0.2) {
                        i.remove();
                        j.remove();
                    } else if (r.nextFloat() < 0.2) {
                        Object T = genKey();
                        i.set(T);
                        j.set((T));
                    } else if (r.nextFloat() < 0.2) {
                        Object T = genKey();
                        i.add(T);
                        j.add((T));
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
            ObjectBigListIterator i;
            ObjectBigListIterator j;
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
                        Object T = genKey();
                        i.set(T);
                        j.set((T));
                    } else if (r.nextFloat() < 0.2) {
                        Object T = genKey();
                        i.add(T);
                        j.add((T));
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
                        Object T = genKey();
                        i.set(T);
                        j.set((T));
                    } else if (r.nextFloat() < 0.2) {
                        Object T = genKey();
                        i.add(T);
                        j.add((T));
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

    @SuppressWarnings({"unchecked"})
    protected static void test(int n) {
        ObjectBigArrayBigList m = new ObjectBigArrayBigList();
        ObjectBigList t = ObjectBigLists.asBigList(new ObjectArrayList());
        k = new Object[n];
        nk = new Object[n];
        kt = new Object[n];
        nkt = new Object[n];
        for (int i = 0; i < n; i++) {
            k[i] = kt[i] = genKey();
            nk[i] = nkt[i] = genKey();
        }
		/* We add pairs to t. */
        for (int i = 0; i < n; i++) t.add(k[i]);
		/* We add to m the same data */
        m.addAll(t);
        testLists(m, t, n, 0);

        // This tests all reflection-based methods.
        m = ObjectBigArrayBigList.wrap(ObjectBigArrays.EMPTY_BIG_ARRAY);
        t = ObjectBigLists.asBigList(new ObjectArrayList());
		/* We add pairs to t. */
        for (int i = 0; i < n; i++) t.add(k[i]);
		/* We add to m the same data */
        m.addAll(t);
        testLists(m, t, n, 0);
    }

    @Test
    void test1() {
        test(1);
    }

    @Test
    void test10() {
        test(10);
    }

    @Test
    void test100() {
        test(100);
    }

    @Test(enabled = false, description = "Too long")
    void test1000() {
        test(1000);
    }
}
