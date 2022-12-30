#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using priority queue (min heap); time complexity: O(nlogn)
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        if (temperatures.size() == 1)
            return {0};
        vector<int> res(temperatures.size(), 0);
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> minHeap;
        for (int i = 1; i < temperatures.size(); i++) {
            if (temperatures[i - 1] < temperatures[i])
                res[i - 1] = 1;
            else
                minHeap.push(pair<int, int>(temperatures[i - 1], i - 1));
            while (!minHeap.empty() && minHeap.top().first < temperatures[i]) {
                auto p = minHeap.top();
                minHeap.pop();
                res[p.second] = i - p.second;
            }
        }
        return res;
    }

    // O(n) time complexity and O(1) space complexity solution
    vector<int> dailyTemperatures2(vector<int>& temperatures) {
        int n = temperatures.size();
        int hottest = 0;
        vector<int> res(temperatures.size(), 0);
        for (int i = n - 1; i >= 0; i--) {
            int curTemp = temperatures[i];
            if (curTemp >= hottest) {
                hottest = curTemp;
                continue;
            }
            int days = 1;
            while (temperatures[i + days] <= curTemp)
                days += res[i + days];
            res[i] = days;
        }
        return res;
    }
};