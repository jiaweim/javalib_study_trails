package tutorial.lib.guava.samples;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 30 Nov 2017, 3:40 PM
 */
public class ComparatorChainTest
{

    @Test
    public void testChain()
    {
        List<ToOrder> orders = new ArrayList<>();
        orders.add(new ToOrder(null, "1", 2.0));
        orders.add(new ToOrder(1, "3", 3.0));
        orders.add(new ToOrder(2, "5", 5.0));
        orders.add(new ToOrder(2, "5", 4.0));
        orders.add(new ToOrder(2, "5", 6.0));
        orders.add(new ToOrder(2, "5", 7.0));
        orders.add(new ToOrder(4, "2", 1.0));
        orders.add(new ToOrder(2, "8", null));

        Comparator<ToOrder> comparator = (o1, o2) -> ComparisonChain.start().compare(o1.getV1().orElse(null), o2.getV1().orElse(null), Ordering.natural().nullsLast())
                .compare(o1.getV3(), o2.getV3(), Ordering.natural().reverse().nullsLast())
                .compare(o1.getV2(), o2.getV2()).result();

        orders.sort(comparator);
        for (ToOrder order : orders)
            System.out.println(order);

    }
}
