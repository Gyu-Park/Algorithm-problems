#include <iostream>
#include <numeric>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:  // bfs solution
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        if (source == destination)
            return true;
        vector<vector<int>> adj(n);
        for (auto& edge : edges) {
            adj[edge[0]].push_back(edge[1]);
            adj[edge[1]].push_back(edge[0]);
        }

        queue<int> q;
        q.push(source);
        vector<bool> visited(n);
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            if (visited[node])
                continue;
            visited[node] = true;
            for (int i = 0; i < adj[node].size(); i++) {
                if (adj[node][i] == destination)
                    return true;
                q.push(adj[node][i]);
            }
        }
        return false;
    }

    // solution using disjoint set union (dsu)
    bool validPath2(int n, vector<vector<int>>& edges, int source, int destination) {
        UnionFind uf(n);

        for (auto& edge : edges) {
            uf.join(edge[0], edge[1]);
        }

        return uf.find(source) == uf.find(destination);
    }

    class UnionFind {
        vector<int> root, rank;

       public:
        UnionFind(int n) : root(n), rank(n, 1) {
            iota(root.begin(), root.end(), 0);
        }
        int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }
        void join(int x, int y) {
            int rootX = find(x), rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    swap(rootX, rootY);
                }
                // Modify the root of the smaller group as the root of the
                // larger group, also increment the size of the larger group.
                root[rootX] = rootY;
                rank[rootY] += rank[rootX];
            }
        }
    };
};