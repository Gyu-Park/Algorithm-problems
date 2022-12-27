from typing import List


class Solution:
    def maximumBags(self, capacity: List[int], rocks: List[int], additionalRocks: int) -> int:
        for i in range(len(capacity)):
            capacity[i] = capacity[i] - rocks[i]
        res = 0
        for cap in sorted(capacity):
            additionalRocks -= cap
            if additionalRocks < 0:
                break
            res += 1
        return res

    # shorter version
    def maximumBags2(self, capacity, rocks, x):
        count = sorted(c - r for c, r in zip(capacity, rocks))[::-1]
        while count and x and count[-1] <= x:
            x -= count.pop()
        return len(rocks) - len(count)


print(Solution.maximumBags2(Solution, [2, 3, 4, 5], [1, 2, 4, 4], 2))
