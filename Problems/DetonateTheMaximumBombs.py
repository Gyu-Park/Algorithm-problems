from typing import Collection, List


class Solution:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        # dfs
        graph = Collection.defaultdict(list)

        for i, b in enumerate(bombs):
            for j, bb in enumerate(bombs):
                if i == j:
                    continue
                if (bb[0] - b[0])**2 + (bb[1] - b[1])**2 <= b[2]**2:
                    graph[i].append(j)

        def dfs(i):
            ret = 0
            for j in graph[i]:
                if j not in bombs_set:
                    bombs_set.add(j)
                    ret += dfs(j) + 1
            return ret

        res = 0
        for i in range(len(bombs)):
            bombs_set = set()
            bombs_set.add(i)
            res = max(res, dfs(i) + 1)

        return res
