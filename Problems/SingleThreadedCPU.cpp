#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:
    vector<int> getOrder(vector<vector<int>>& tasks) {
        for (int i = 0; i < tasks.size(); i++)
            tasks[i].push_back(i);
        sort(tasks.begin(), tasks.end());
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> waitList;
        vector<int> res;
        waitList.push(pair<int, int>(tasks[0][1], tasks[0][2]));
        long enqueueTime = tasks[0][0];
        int tasksIndex = 1;
        while (!waitList.empty() || tasksIndex < tasks.size()) {
            if (waitList.size() == 0 && enqueueTime < tasks[tasksIndex][0])
                enqueueTime = tasks[tasksIndex][0];
            while (tasksIndex < tasks.size() && tasks[tasksIndex][0] <= enqueueTime) {
                waitList.push(pair<int, int>(tasks[tasksIndex][1], tasks[tasksIndex][2]));
                tasksIndex++;
            }
            auto [processingTime, index] = waitList.top();
            waitList.pop();
            enqueueTime += processingTime;
            res.push_back(index);
        }
        return res;
    }
};