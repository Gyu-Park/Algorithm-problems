package Problems;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        // new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int stone : stones)
            pq.offer(stone);

        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();

            if (x == y)
                continue;
            pq.offer(x - y);
        }

        return pq.isEmpty() == true ? 0 : pq.poll();
    }
}
