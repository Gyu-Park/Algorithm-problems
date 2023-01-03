#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using disjoint set
    int earliestAcq(vector<vector<int>>& logs, int n) {
        sort(logs.begin(), logs.end());
        UnionFind uf(n);
        for (int i = 0; i < logs.size(); i++) {
            uf.group(logs[i][1], logs[i][2]);
            if (uf.groups == 1)
                return logs[i][0];
        }
        return -1;
    }

    class UnionFind {
       public:
        vector<int> root;
        vector<int> rank;
        int groups;
        UnionFind(int numOfPeople) {
            for (int i = 0; i < numOfPeople; i++) {
                root.push_back(i);
            }
            rank = vector<int>(numOfPeople, 1);
            groups = numOfPeople;
        }

        int find(int x) {
            if (root[x] == x) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        void group(int x, int y) {
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
            groups--;
        }

        bool isConnected(int x, int y) {
            return find(x) == find(y);
        }
    };
};