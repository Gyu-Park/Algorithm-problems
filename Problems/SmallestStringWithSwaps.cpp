#include <algorithm>
#include <array>
#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using disjoint set (Union Find)
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

        unordered_map<int, array<int, 26>> umap;
        for (int i = 0; i < s.size(); i++) {
            umap[uf.find(i)][s[i] - 'a']++;
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

    // optimized solution
    string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end());
        UnionFind uf(s.size());
        for (auto& a : pairs) {
            uf.group(a[0], a[1]);
        }

        unordered_map<int, vector<int>> group;
        // Group the vertices that are in the same component
        for (int vertex = 0; vertex < s.size(); vertex++) {
            int root = uf.find(vertex);
            // Add the vertices corresponding to the component root
            group[root].push_back(vertex);
        }

        // String to store the answer
        string res(s.length(), ' ');
        // Iterate over each component
        for (auto component : group) {
            vector<int> indices = component.second;

            // Sort the characters in the group
            vector<char> characters;
            for (int index : indices) {
                characters.push_back(s[index]);
            }
            sort(characters.begin(), characters.end());

            // Store the sorted characters
            for (int index = 0; index < indices.size(); index++) {
                res[indices[index]] = characters[index];
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
    };
};