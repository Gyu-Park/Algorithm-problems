#include <bits/stdc++.h>
using namespace std;

class Solution {
   public:
    // Time complexity: O(EV log EV)
    int minCostToSupplyWater(int n, vector<int>& wells, vector<vector<int>>& pipes) {
        // use Prim's algorithm
        // if making a virtual node 0, can make a graph with weighted edges
        // (wells become weighted edges from the virtual node 0)

        // build adjacency list
        vector<vector<pair<int, int>>> adj(n + 1);
        for (int i = 0; i < wells.size(); i++) {
            adj[0].push_back({wells[i], i + 1});
        }

        for (auto pipe : pipes) {
            adj[pipe[0]].push_back({pipe[2], pipe[1]});
            adj[pipe[1]].push_back({pipe[2], pipe[0]});
        }

        int res = 0;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        unordered_set<int> visited;
        pq.push({0, 0});
        while (!pq.empty() && visited.size() < n + 1) {  // O(ev) at most
            int node = pq.top().second;
            int cost = pq.top().first;
            pq.pop();
            if (visited.find(node) != visited.end())
                continue;
            visited.insert(node);
            res += cost;
            vector<pair<int, int>> adjList = adj[node];
            for (int i = 0; i < adjList.size(); i++) {  // O(logev)
                if (visited.find(adjList[i].second) == visited.end())
                    pq.push(adjList[i]);
            }
        }

        return res;
    }

    // Kruskal's algorithm with union find
    vector<int> uf;
    int minCostToSupplyWater(int n, vector<int>& wells, vector<vector<int>>& pipes) {
        uf.resize(n + 1, 0);
        for (auto& p : pipes) swap(p[0], p[2]);
        for (int i = 0; i < n; ++i) {
            uf[i + 1] = i + 1;
            pipes.push_back({wells[i], 0, i + 1});
        }
        sort(pipes.begin(), pipes.end());

        int res = 0;
        for (int i = 0; n > 0; ++i) {
            int x = find(pipes[i][1]), y = find(pipes[i][2]);
            if (x != y) {
                res += pipes[i][0];
                uf[x] = y;
                --n;
            }
        }
        return res;
    }

    int find(int x) {
        if (x != uf[x]) uf[x] = find(uf[x]);
        return uf[x];
    }
};