#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        // bfs solution
        // have to visit all the cities that can be reached from city 1
        // and every edge, do min(edge1, edge2);
        // after visiting all the cities that can be reached from city 1,
        // return the minimum score of the path

        int res = INT_MAX;
        vector<vector<pair<int, int>>> adj(n + 1);
        for (int i = 0; i < roads.size(); i++) {
            adj[roads[i][0]].push_back({roads[i][1], roads[i][2]});
            adj[roads[i][1]].push_back({roads[i][0], roads[i][2]});
        }

        vector<int> visited(n + 1, 0);
        queue<int> q;
        q.push(1);
        visited[1] = 1;
        while (!q.empty()) {
            int city = q.front(); q.pop();
            for (auto neighbor : adj[city]) {
                if (visited[neighbor.first] == 0) {
                    visited[city] = 1;
                    q.push(neighbor.first);
                    res = min(res, neighbor.second);
                }
            }
        }

        return res;
    }
};