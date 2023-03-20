#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <vector>
using namespace std;

class Solution {
   public:
    int dfs(int cur, unordered_map<int, vector<int>>& adjList, vector<bool>& hasApple, unordered_set<int>& visited) {
        vector<int> adj = adjList[cur];
        int sum = 0;
        for (int i = 0; i < adj.size(); i++) {
            if (visited.find(adj[i]) != visited.end())
                continue;
            visited.insert(adj[i]);
            sum += dfs(adj[i], adjList, hasApple, visited);
        }
        if ((hasApple[cur] || sum > 0) && cur != 0)
            return sum + 2;
        return sum;
    }

    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        unordered_set<int> visited;
        unordered_map<int, vector<int>> adjList;
        for (int i = 0; i < edges.size(); i++) {
            adjList[edges[i][0]].push_back(edges[i][1]);
            adjList[edges[i][1]].push_back(edges[i][0]);
        }
        visited.insert(0);
        return dfs(0, adjList, hasApple, visited);
    }
};