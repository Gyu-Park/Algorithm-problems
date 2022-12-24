import sys
from typing import List


class Solution:

    def assignBikes(workers: List[List[int]], bikes: List[List[int]]) -> int:

        def dist(x, y):
            return abs(x[0] - y[0]) + abs(x[1] - y[1])

        def calculate(i, visited, cache):
            if i == len(workers):
                return 0
            if (i, visited) in cache:
                return cache[(i, visited)]
            ans = float('inf')
            for j, bike in enumerate(bikes):
                if visited & 1 << j:
                    continue
                ans = min(
                    ans, dist(bike, workers[i]) + calculate(i+1, visited | 1 << j, cache))
            cache[(i, visited)] = ans
            return ans

        return calculate(0, 0, {})

    memo = [-1 for _ in range(1025)]

    def assignBikes2(self, workers: List[List[int]], bikes: List[List[int]]) -> int:
        return self.helper(self, workers, bikes, 0, 0)

    def dist2(self, x, y):
        return abs(x[0] - y[0]) + abs(x[1] - y[1])

    def helper(self, workers, bikes, workerIndex, visited) -> int:
        if workerIndex >= len(workers):
            return 0
        if self.memo[visited] != -1:
            return self.memo[visited]
        res = sys.maxsize
        for i, bike in enumerate(bikes):
            if visited & (1 << i) != 0:
                continue
            res = min(res, self.dist2(self, workers[workerIndex], bike) + self.helper(
                self, workers, bikes, workerIndex + 1, (visited | (1 << i))))
            self.memo[visited] = res
        return res


workers = [[0, 0], [1, 0], [2, 0], [3, 0], [4, 0]]
bikes = [[0, 999], [1, 999], [2, 999], [3, 999], [4, 999]]
print(Solution.assignBikes2(Solution, workers, bikes))
