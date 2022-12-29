#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using Khan's Algorithm (Topological sorting)
    int minimumSemesters(int n, vector<vector<int>>& relations) {
        if (n == 1)
            return 1;
        vector<int> inDegree(n + 1, 0);
        unordered_map<int, vector<int>> outDegree;
        for (auto& relation : relations) {
            inDegree[relation[1]]++;
            outDegree[relation[0]].push_back(relation[1]);
        }
        queue<int> q;
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0)
                q.push(i);
        }
        int takenSem = 0;
        int sz = q.size();
        while (sz-- > 0) {
            int course = q.front();
            q.pop();
            vector<int> adjList = outDegree[course];
            for (auto& c : adjList) {
                inDegree[c]--;
                if (inDegree[c] == 0)
                    q.push(c);
            }
            if (sz == 0) {
                sz = q.size();
                takenSem++;
            }
        }
        for (int i : inDegree) {
            if (i != 0)
                return -1;
        }
        return takenSem;
    }
};