#include <iostream>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using disjoint set a.k.a. Union Find
    bool validTree(int n, vector<vector<int>>& edges) {
        UnionFind uf(n);
        for (int i = 0; i < edges.size(); i++) {
            if (uf.isConnected(edges[i][0], edges[i][1])) {
                return false;
            }
            uf.unionNodes(edges[i][0], edges[i][1]);
        }
        return uf.numOfTree == 1;
    }
    class UnionFind {
       public:
        vector<int> root;
        vector<int> rank;
        int numOfTree;
        UnionFind(int nodes) {
            for (int i = 0; i < nodes; i++) {
                root.push_back(i);
            }
            rank = vector<int>(nodes, 1);
            numOfTree = nodes;
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
            numOfTree--;
        }

        bool isConnected(int x, int y) {
            return find(x) == find(y);
        }
    };
};