#include <functional>
#include <iostream>
#include <map>
#include <vector>
using namespace std;

class Solution {
   public:  // dfs solution
    void dfs(vector<vector<int>>& graph, vector<vector<int>>& res, vector<int>& paths, int node) {
        if (node == graph.size() - 1) {
            res.push_back(vector<int>(paths));
            return;
        }
        for (auto& path : graph[node]) {
            paths.push_back(path);
            dfs(graph, res, paths, path);
            paths.pop_back();
        }
    }

    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        vector<vector<int>> res;
        vector<int> paths;
        paths.push_back(0);
        dfs(graph, res, paths, 0);
        return res;
    }

    // dp solution
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        int target = int(graph.size()) - 1;
        map<int, vector<vector<int>>> memo;
        function<vector<vector<int>>(int)> allPathsToTarget = [&](int currNode) {
            if (memo.count(currNode)) return memo[currNode];
            vector<vector<int>> results;
            if (currNode == target) {
                results.push_back(vector<int>{target});
            } else {
                for (int nextNode : graph[currNode]) {
                    for (vector<int>& path : allPathsToTarget(nextNode)) {
                        vector<int> newPath{currNode};
                        newPath.insert(newPath.end(), path.begin(), path.end());
                        results.push_back(newPath);
                    }
                }
            }
            memo[currNode] = results;
            return results;
        };
        return allPathsToTarget(0);
    }
};