#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:
    int scheduleCourse(vector<vector<int>>& courses) {
        // greedy solution
        // time complexity: O(nlogn)
        // space complexity: O(n)
        sort(courses.begin(), courses.end(), [](auto& a, auto& b) {
            return a[1] < b[1];
        });

        priority_queue<int> pq;
        int curDur = 0;
        for (int i = 0; i < courses.size(); i++) {
            curDur += courses[i][0];
            pq.push(courses[i][0]);
            if (curDur > courses[i][1]) {
                int maxDur = pq.top();
                pq.pop();
                curDur -= maxDur;
            }
        }

        return pq.size();
    }
};