import collections
from typing import List


def eventualSafeNodes(graph: List[List[int]]) -> List[int]:
    # topological sort solution
    n = len(graph)
    out_degree = [0] * n
    in_nodes = collections.defaultdict(list)
    queue = []
    for i in range(n):
        out_degree[i] = len(graph[i])
        if out_degree[i] == 0:
            queue.append(i)
        for j in graph[i]:
            in_nodes[j].append(i)
    for term_node in queue:
        for in_node in in_nodes[term_node]:
            out_degree[in_node] -= 1
            if out_degree[in_node] == 0:
                queue.append(in_node)
    return sorted(queue)


def eventualSafeNodes1(graph: List[List[int]]) -> List[int]:
    # dfs solution
    color = [0] * len(graph)

    def dfs(i):
        if color[i]:
            return color[i] == 1
        color[i] = 2
        for j in graph[i]:
            if not dfs(j):
                return False
        color[i] = 1
        return True

    for i in range(len(graph)):
        dfs(i)
    return [i for i, v in enumerate(color) if v == 1]


graph = [[1, 2, 3, 4], [1, 2], [3, 4], [0, 4], []]
print(eventualSafeNodes1(graph))
