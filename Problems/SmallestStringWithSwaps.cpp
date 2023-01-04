#include <algorithm>
#include <array>
#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:
    string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
        // loop through string s and put each char into minheap with its corresponding index
        // loop through pairs and group overlapping indices using Union Find
        // build a string using loop
        // check if top letter in minheap is a member of group that an index is in
        sort(pairs.begin(), pairs.end());
        UnionFind uf(s.size());
        for (auto& a : pairs) {
            uf.group(a[0], a[1]);
        }

        uf.updateRoot();

        unordered_map<int, array<int, 26>> umap;
        for (int i = 0; i < s.size(); i++) {
            // if (umap.find(uf.root[i]) == umap.end()) {
            //     umap[uf.root[i]] = array<int>(26);
            // } else {
            umap[uf.root[i]][s[i] - 'a']++;
            // }
        }

        string res = "";
        for (int i = 0; i < s.size(); i++) {
            for (int j = 0; j < 26; j++) {
                if (umap[uf.root[i]][j] > 0) {
                    res += 'a' + j;
                    umap[uf.root[i]][j]--;
                    break;
                }
            }
        }
        return res;
    }

    class UnionFind {
       public:
        vector<int> root;
        vector<int> rank;

        UnionFind(int x) {
            for (int i = 0; i < x; i++) {
                root.push_back(i);
            }
            rank = vector<int>(x, 1);
        }

        int find(int x) {
            if (root[x] == x) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        void group(int x, int y) {
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

        bool isConnected(int x, int y) {
            return find(x) == find(y);
        }

        void updateRoot() {
            for (int i = 0; i < root.size(); i++) {
                find(i);
            }
        }
    };
};