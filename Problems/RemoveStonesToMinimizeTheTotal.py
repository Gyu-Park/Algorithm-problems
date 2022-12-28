import heapq
from typing import List


class Solution:
    def minStoneSum(self, piles: List[int], k: int) -> int:
        sumStones = sum(piles)
        piles = [-x for x in piles]
        heapq.heapify(piles)
        while k > 0 and sumStones > 1:
            i = -heapq.heappop(piles)
            sumStones -= i // 2
            i -= i // 2
            heapq.heappush(piles, -i)
            k -= 1
        return sumStones

    # shorter version
    def minStoneSum(self, piles: List[int], k: int) -> int:
        piles = [-x for x in piles]
        heapq.heapify(piles)
        for _ in range(k):
            heapq.heapreplace(piles, piles[0] // 2)
        return -sum(piles)
