package tutorial.lib.guava;

import com.google.common.collect.EvictingQueue;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 13 Aug 2020, 3:31 PM
 */
public class EvictingQueueTest
{
    void test()
    {
        EvictingQueue<Integer> queue = EvictingQueue.create(3);
        queue.add(1);

    }
}
