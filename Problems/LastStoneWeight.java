package Problems;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    // max heap solution
    // time complexity: O(nlogn)
    // space complexity: O(n)
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

    // counting sort solution
    // time complexity: O(max(n, m)), m = indices of the cnt array
    // space complexity: O(n)
    public int lastStoneWeight2(int[] stones) {
        int max = 0;
        for (int s : stones) {
            max = Math.max(max, s);
        }

        int[] cnt = new int[max + 1];
        for (int s : stones) {
            cnt[s]++;
        }

        int biggest = 0;
        int curW = max;
        while (curW > 0) {
            if (cnt[curW] == 0) {
                curW--;
            } else if (biggest == 0) {
                cnt[curW] %= 2;
                if (cnt[curW] == 1)
                    biggest = curW;
                curW--;
            } else {
                cnt[curW]--;
                biggest -= curW;
                if (biggest > curW)
                    continue;
                cnt[biggest]++;
                biggest = 0;
            }
        }

        return biggest;
    }
}
