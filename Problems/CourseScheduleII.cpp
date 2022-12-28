#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using Topological sorting
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<int> inDegree(numCourses, 0);
        unordered_map<int, vector<int>> outDegree;
        for (auto& courses : prerequisites) {
            inDegree[courses[0]]++;
            outDegree[courses[1]].push_back(courses[0]);
        }
        queue<int> q;
        for (int i = 0; i < inDegree.size(); i++) {
            if (inDegree[i] == 0) {
                q.push(i);
            }
        }
        vector<int> res;
        while (!q.empty()) {
            int course = q.front();
            q.pop();
            res.push_back(course);
            vector<int> v = outDegree[course];
            for (int i : v) {
                inDegree[i]--;
                if (inDegree[i] == 0)
                    q.push(i);
            }
        }
        return res.size() == numCourses ? res : vector<int>();
    }
};