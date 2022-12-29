#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:
    // chronologically order each start and end times and count the maximum overlapping time
    int minMeetingRooms(vector<vector<int>> &intervals) {
        map<int, int> m;
        for (auto &t : intervals)
            m[t[0]]++, m[t[1]]--;
        int cur = 0, res = 0;
        for (auto &it : m)
            res = max(res, cur += it.second);
        return res;
    }

    // solution using min heap
    int minMeetingRooms(vector<vector<int>> &intervals) {
        sort(intervals.begin(),
             intervals.end(),
             [](const vector<int> &a, const vector<int> &b) {
                 return a[0] < b[0];
             });
        priority_queue<int, vector<int>, greater<int>> minHeap;
        minHeap.push(intervals[0][1]);
        for (int i = 1; i < intervals.size(); i++) {
            // this "if" allows to update end time, meaning that we don't need an extra room
            // if start time is before the end time in minheap, just push its end time, meaning that we need an extra room
            if (intervals[i][0] >= minHeap.top())
                minHeap.pop();
            minHeap.push(intervals[i][1]);
        }
        return minHeap.size();
    }
};