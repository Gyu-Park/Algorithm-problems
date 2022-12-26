import sys
from typing import List


# bfs solution
class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # defaultdict(list) can be a better choice
        adj = [[] for _ in range(n + 1)]
        for u, v, w in times:
            adj[u].append([w, v])

        table = [sys.maxsize for _ in range(n)]
        table[k] = 0
        queue = []
        queue.append(k)
        while len(queue) > 0:
            curNode = queue.pop(0)
            for nodes in adj[curNode]:
                nNode = nodes[1]
                time = nodes[0]
                if table[nNode] > time + table[curNode]:
                    table[nNode] = time + table[curNode]
                    queue.append(nNode)

        res = -sys.maxsize - 1
        for i in range(1, n + 1):
            res = max(res, table[i])
        return res if res != sys.maxsize else -1
