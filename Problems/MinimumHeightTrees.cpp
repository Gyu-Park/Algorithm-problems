#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:
    vector<int> findMinHeightTrees(int n, vector<vector<int>>& edges) {
        if (n == 1)
            return vector<int>(1, 0);
        else if (n == 2)
            return edges[0];
        vector<int> numEdges(n, 0);
        unordered_map<int, vector<int>> umap;
        for (auto& edge : edges) {
            numEdges[edge[0]]++;
            numEdges[edge[1]]++;
            umap[edge[0]].push_back(edge[1]);
            umap[edge[1]].push_back(edge[0]);
        }
        queue<int> q;
        for (int i = 0; i < numEdges.size(); i++) {
            if (numEdges[i] == 1)
                q.push(i);
        }
        int sz = q.size();
        while (sz-- != 0) {
            n--;
            auto v = q.front();
            q.pop();
            numEdges[v]--;
            vector<int> adj = umap[v];
            for (auto& i : adj) {
                numEdges[i]--;
                if (numEdges[i] == 1)
                    q.push(i);
            }
            if (sz == 0) {
                sz = q.size();
                if (n <= 2 && sz == n)
                    break;
            }
        }
        vector<int> res;
        while (!q.empty()) {
            res.push_back(q.front());
            q.pop();
        }
        return res;
    }
};

int main(void) {
    Solution a;
    vector<vector<int>> tree = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
    vector<int> res = a.findMinHeightTrees(6, tree);
    for (int i : res) {
        cout << i << endl;
    }
}