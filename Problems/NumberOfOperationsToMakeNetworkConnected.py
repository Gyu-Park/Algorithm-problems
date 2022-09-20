from typing import List


def makeConnected(n: int, connections: List[List[int]]) -> int:
    if len(connections) < n - 1:
        return -1
    s = ''.join(map(chr, range(n)))
    for a, b in connections:
        s = s.replace(s[a], s[b])
    return len(set(s)) - 1

def makeConnected1(n, connections):
    if len(connections) < n - 1: return -1
    G = [set() for i in range(n)]
    for i, j in connections:
        G[i].add(j)
        G[j].add(i)
    seen = [0] * n

    def dfs(i):
        if seen[i]: return 0
        seen[i] = 1
        for j in G[i]: dfs(j)
        return 1

    return sum(dfs(i) for i in range(n)) - 1

connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
print(makeConnected(6, connections)) 