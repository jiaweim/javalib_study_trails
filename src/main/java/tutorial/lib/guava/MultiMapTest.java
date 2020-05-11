package tutorial.lib.guava;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 22 Nov 2017, 4:29 PM
 */
public class MultiMapTest
{
    @Test
    public void test()
    {

        SetMultimap<String, String> map = HashMultimap.create();

        map.put("1", "2");
        map.put("1", "3");
        map.put("1", "4");
        map.put("1", "5");

        Set<String> strings = map.get("1");
        System.out.println(strings.size());

        strings.remove("2");
        strings.remove("3");

        System.out.println(strings.size());

        System.out.println(map.get("1").size());

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);
        set2.add(1);

        System.out.println(set1.equals(set2));

    }

}
