#include <bits/stdc++.h>
using namespace std;

class UnionFind {
   public:
    vector<int> root;
    vector<int> rank;
    int groups;

    UnionFind(int n) {
        for (int i = 0; i < n; i++) {
            root.push_back(i);
            rank.push_back(1);
        }
        groups = n;
    }

    int find(int x) {
        if (root[x] == x)
            return x;

        return root[x] = find(root[x]);
    }

    void uni(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] > rank[yRoot]) {
            root[yRoot] = xRoot;
        } else if (rank[xRoot] < rank[yRoot]) {
            root[xRoot] = yRoot;
        } else {
            root[yRoot] = xRoot;
            rank[xRoot]++;
        }
        groups--;
    }

    bool isUnion(int x, int y) {
        return find(x) == find(y);
    }
};

class Solution {
   public:
    int makeConnected(int n, vector<vector<int>>& connections) {
        UnionFind uf(n);

        int extraCables = 0;
        for (auto connection : connections) {
            if (uf.isUnion(connection[0], connection[1])) {
                extraCables++;
            } else {
                uf.uni(connection[0], connection[1]);
            }
        }

        if (extraCables >= uf.groups - 1 || uf.groups == 1)
            return uf.groups - 1;
        return -1;
    }
};