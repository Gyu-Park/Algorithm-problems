#include <iostream>
#include <vector>
using namespace std;

class Solution {
   public:
    int dfs(vector<vector<int>>& children, string& s, int cur, int& res) {
        int long1 = 0;
        int long2 = 0;
        int maxPath = 0;
        for (int i = 0; i < children[cur].size(); i++) {
            int path = dfs(children, s, children[cur][i], res);

            if (s[children[cur][i]] == s[cur])
                continue;

            if (path > long1)
                long1 = path;
            if (long1 > long2) {
                long1 = long2;
                long2 = path;
            }
        }

        res = max(res, long1 + long2 + 1);
        return long2 + 1;
    }

    int longestPath(vector<int>& parent, string s) {
        // dfs
        // check if letters are the same between parent and child nodes
        // max(res, left + right  + 1) for both paths
        // when return to its parent, choose longer path between left and right child nodes

        int n = parent.size();
        vector<vector<int>> children(n);
        // Start from node 1, since root node 0 does not have a parent.
        for (int i = 1; i < n; i++) {
            children[parent[i]].push_back(i);
        }
        int res = 0;
        dfs(children, s, 0, res);
        return res;
    }
};