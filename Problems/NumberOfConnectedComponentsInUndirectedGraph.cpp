#include <iostream>
#include <vector>
using namespace std;

class Solution {
   public:  // dfs solution
    void helper(int component, vector<int> adj[], vector<int>& visited) {
        visited[component] = 1;
        for (int i = 0; i < adj[component].size(); i++) {
            if (visited[adj[component][i]] == 0) {
                helper(adj[component][i], adj, visited);
            }
        }
    }

    int countComponents(int n, vector<vector<int>>& edges) {
        vector<int> adj[n];
        for (int i = 0; i < edges.size(); i++) {
            adj[edges[i][0]].push_back(edges[i][1]);
            adj[edges[i][1]].push_back(edges[i][0]);
        }

        vector<int> visited(n, 0);
        int connectedComponents = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                helper(i, adj, visited);
                connectedComponents++;
            }
        }
        return connectedComponents;
    }

    // anpther solution using Union Find
    int countComponents2(int n, vector<vector<int>>& edges) {
        UnionFind uf(n);
        for (int i = 0; i < edges.size(); i++) {
            uf.unionNodes(edges[i][0], edges[i][1]);
        }
        return uf.components;
    }

    class UnionFind {
       public:
        vector<int> root;
        vector<int> rank;
        int components;
        UnionFind(int nodes) {
            for (int i = 0; i < nodes; i++) {
                root.push_back(i);
            }
            rank = vector<int>(nodes, 1);
            components = nodes;
        }

        int find(int x) {
            if (root[x] == x) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        void unionNodes(int x, int y) {
            auto xRoot = find(x);
            auto yRoot = find(y);
            if (xRoot == yRoot)
                return;
            if (rank[xRoot] > rank[yRoot]) {
                root[yRoot] = xRoot;
            } else if (rank[xRoot] < rank[yRoot]) {
                root[xRoot] = yRoot;
            } else {
                root[yRoot] = xRoot;
                rank[xRoot]++;
            }
            components--;
        }

        bool isConnected(int x, int y) {
            return find(x) == find(y);
        }
    };
};