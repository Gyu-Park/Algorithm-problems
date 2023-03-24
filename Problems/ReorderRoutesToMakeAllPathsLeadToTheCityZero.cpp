#include <bits/stdc++.h>
using namespace std;

class Solution {
   public:
    int minReorder(int n, vector<vector<int>>& connections) {
        vector<int> visited(n, 0);
        vector<vector<int>> adjOut(n);
        vector<vector<int>> adjIn(n);
        queue<int> q;
        int res = 0;
        for (auto c : connections) {
            if (c[0] == 0) {
                q.push(c[1]);
                res++;
            } else if (c[1] == 0) {
                q.push(c[0]);
            }
            adjOut[c[0]].push_back(c[1]);
            adjIn[c[1]].push_back(c[0]);
        }

        visited[0] = 1;
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            visited[node] = 1;
            vector<int> out = adjOut[node];
            vector<int> in = adjIn[node];
            for (auto i : out) {
                if (visited[i] == 0) {
                    q.push(i);
                    res++;
                }
            }

            for (auto j : in) {
                if (visited[j] == 0) {
                    q.push(j);
                }
            }
        }

        return res;
    }
};