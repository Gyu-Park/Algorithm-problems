import heapq
from typing import List


def connectSticks(sticks: List[int]) -> int:
    if len(sticks) == 1:
        return 0
    heapq.heapify(sticks)
    res = 0
    while len(sticks) > 1:
        first = heapq.heappop(sticks)
        second = heapq.heappop(sticks)
        sum = first + second
        res += sum
        heapq.heappush(sticks, sum)
    return res


print(connectSticks([1, 8, 3, 5]))
