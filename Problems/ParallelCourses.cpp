#include <iostream>
#include <queue>
#include <set>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using Khan's Algorithm (Topological sorting)
    int minimumSemesters(int n, vector<vector<int>>& relations) {
        vector<int> inDegree(n + 1, 0);
        unordered_map<int, vector<int>> outDegree;
        for (auto& relation : relations) {
            inDegree[relation[1]]++;
            outDegree[relation[0]].push_back(relation[1]);
        }
        queue<int> q;
        for (int i = 1; i <= n; i++)
            if (inDegree[i] == 0) q.push(i);
        int takenSem = 0, sz = q.size(), takenCourses = 0;
        while (sz-- > 0) {
            takenCourses++;
            int course = q.front();
            q.pop();
            vector<int> adjList = outDegree[course];
            for (auto& c : adjList)
                if (--inDegree[c] == 0) q.push(c);
            if (sz == 0 && ++takenSem) sz = q.size();
        }
        return takenCourses == n ? takenSem : -1;
    }

    // shorter solution
    int minimumSemesters2(int n, vector<vector<int>>& relations) {
        vector<int> indegree(n, 0);
        vector<set<int>> g(n);
        for (auto& v : relations) {
            g[v[0] - 1].insert(v[1] - 1);
            indegree[v[1] - 1]++;
        }
        int maxDepth = 0, numStudied = 0;
        queue<pair<int, int>> q;  // vertex, depth
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0) q.push({i, 1});
        for (; !q.empty(); numStudied++) {
            auto [i, depth] = q.front();
            q.pop();
            maxDepth = depth;
            for (auto j : g[i])
                if (--indegree[j] == 0) q.push({j, depth + 1});
        }
        return numStudied == n ? maxDepth : -1;
    }
};