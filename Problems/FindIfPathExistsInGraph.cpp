#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:  // bfs solution
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        if (source == destination)
            return true;
        vector<vector<int>> adj(n);
        for (auto& edge : edges) {
            adj[edge[0]].push_back(edge[1]);
            adj[edge[1]].push_back(edge[0]);
        }

        queue<int> q;
        q.push(source);
        vector<bool> visited(n);
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            if (visited[node])
                continue;
            visited[node] = true;
            for (int i = 0; i < adj[node].size(); i++) {
                if (adj[node][i] == destination)
                    return true;
                q.push(adj[node][i]);
            }
        }
        return false;
    }
};