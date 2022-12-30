#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using disjoint set data structure
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<vector<int>> adj(n + 1);
        for (int i = 0; i < dislikes.size(); i++) {
            adj[dislikes[i][0]].push_back(dislikes[i][1]);
            adj[dislikes[i][1]].push_back(dislikes[i][0]);
        }

        UnionFind uf(n + 1);
        for (int i = 1; i < adj.size(); i++) {
            vector<int> adjList = adj[i];
            for (int j = 1; j < adjList.size(); j++) {
                if (uf.find(i) == uf.find(adjList[j]))
                    return false;
                uf.unionSet(adjList[0], adjList[j]);
            }
        }
        return true;
    }

    class UnionFind {
       public:
        vector<int> root;
        vector<int> rank;
        UnionFind(int size) {
            root.resize(size);
            rank.resize(size, 0);
            for (int i = 0; i < root.size(); i++) {
                root[i] = i;
            }
        }

        int find(int x) {
            if (root[x] == x) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        void unionSet(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
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
        }
    };

    // another solution using bfs
    bool bfs(int src, vector<vector<int>>& adj, vector<int>& color) {
        queue<int> q;
        q.push(src);
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            int nColor = color[node];
            for (int i = 0; i < adj[node].size(); i++) {
                if (color[adj[node][i]] == nColor)
                    return false;
                if (color[adj[node][i]] == -1) {
                    color[adj[node][i]] = 1 - nColor;
                    q.push(adj[node][i]);
                }
            }
        }
        return true;
    }

    bool possibleBipartition2(int n, vector<vector<int>>& dislikes) {
        vector<vector<int>> adj(n + 1);
        for (int i = 0; i < dislikes.size(); i++) {
            adj[dislikes[i][0]].push_back(dislikes[i][1]);
            adj[dislikes[i][1]].push_back(dislikes[i][0]);
        }
        vector<int> color(n + 1, -1);
        for (int i = 1; i < n + 1; i++) {
            if (color[i] == -1) {
                color[i] = 0;
                if (!bfs(i, adj, color))
                    return false;
            }
        }
        return true;
    }
};