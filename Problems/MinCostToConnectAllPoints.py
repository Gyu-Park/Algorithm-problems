import heapq
from typing import List


class UnionFind:
    def __init__(self, size):
        self.root = [i for i in range(size)]
        self.rank = [1] * size

    def find(self, x):
        if x == self.root[x]:
            return x
        self.root[x] = self.find(self.root[x])
        return self.root[x]

    def union(self, x, y):
        xRoot = self.find(x)
        yRoot = self.find(y)

        if xRoot == yRoot:
            return
        if self.rank[xRoot] > self.rank[yRoot]:
            self.root[yRoot] = xRoot
        elif self.rank[xRoot] < self.rank[yRoot]:
            self.root[xRoot] = yRoot
        else:
            self.root[yRoot] = xRoot
            self.rank[xRoot] += 1

    def isConnected(self, x, y):
        return self.find(x) == self.find(y)


class Solution:
    # Kruskal's algorithm
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        # 1. sort distances between vertices in ascending order.
        # 2. check if a edge makes a cycle; if it's not, choose it.
        # 3. repeat 2 until finding n-1 edges where n is the number of vertices.

        # Input: points = [[3,12],[-2,5],[-4,1]]
        # Return the minimum cost to make all points connected.
        minheap = []

        for i in range(0, len(points) - 1):
            for j in range(i + 1, len(points)):
                # |xi - xj| + |yi - yj|
                dist = abs(points[i][0] - points[j][0]) + \
                    abs(points[i][1] - points[j][1])
                edge = str(i) + "," + str(j)
                heapq.heappush(minheap, (dist, edge))

        res = 0
        countEdge = 0
        uf = UnionFind(len(points))
        while countEdge < len(points) - 1:
            edgeTup = heapq.heappop(minheap)
            dist = edgeTup[0]
            edge = edgeTup[1].split(",")
            if UnionFind.isConnected(uf, int(edge[0]), int(edge[1])):
                continue
            UnionFind.union(uf, int(edge[0]), int(edge[1]))
            res += dist
            countEdge += 1

        return res

    # Prim's algorithm
    def minCostConnectPoints2(self, points: List[List[int]]) -> int:
        # Input: points = [[3,12],[-2,5],[-4,1]]
        # Return the minimum cost to make all points connected.
        firstP = points[0]
        minheap = []
        i = 1
        for i, point in enumerate(points[1:], 1):
            dist = abs(firstP[0] - point[0]) + abs(firstP[1] - point[1])
            edge = "0," + str(i)
            heapq.heappush(minheap, (dist, edge))

        visited = set()
        visited.add(0)
        res = 0
        connected = 0
        uf = UnionFind(len(points))
        while connected < len(points) - 1:
            edgeTup = heapq.heappop(minheap)
            dist = edgeTup[0]
            vertices = edgeTup[1].split(",")
            vertex1 = int(vertices[0])
            vertex2 = int(vertices[1])
            if uf.isConnected(vertex1, vertex2):
                continue
            res += dist
            uf.union(vertex1, vertex2)
            visited.add(vertex2)
            for i, point in enumerate(points):
                if i in visited:
                    continue
                dist = abs(points[vertex2][0] - point[0]) + \
                    abs(points[vertex2][1] - point[1])
                edge = str(vertex2) + "," + str(i)
                heapq.heappush(minheap, (dist, edge))
            connected += 1
        return res


# points = [[5, -17], [-3, -14], [-2, 18], [-14, 15], [-9, -17],
#           [9, -16], [8, -3], [-15, 11], [-12, 17], [6, 6], [4, 3]]
points = [[0, 0], [2, 2], [3, 10], [5, 2], [7, 0]]
print(Solution.minCostConnectPoints2(Solution, points))
