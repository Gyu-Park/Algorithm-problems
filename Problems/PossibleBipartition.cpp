#include <iostream>
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
};