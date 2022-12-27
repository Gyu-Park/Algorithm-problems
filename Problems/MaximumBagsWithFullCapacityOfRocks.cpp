#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

class Solution
{
public:
    // solution using priority queue
    int maximumBags(vector<int> &capacity, vector<int> &rocks, int additionalRocks)
    {
        int res = 0;
        priority_queue<int, vector<int>, greater<int>> pq;
        for (int i = 0; i < capacity.size(); i++)
        {
            int k = capacity[i] - rocks[i];
            if (k > 0)
                pq.push(k);
            else if (k == 0)
                res++;
        }

        while (!pq.empty())
        {
            int k = pq.top();
            pq.pop();
            additionalRocks -= k;
            if (additionalRocks < 0)
                break;
            res++;
        }
        return res;
    }

    // solution using greedy algorithm
    int maximumBags2(vector<int> &capacity, vector<int> &rocks, int additionalRocks)
    {
        for (int i = 0; i < capacity.size(); ++i)
            capacity[i] = capacity[i] - rocks[i];
        sort(capacity.begin(), capacity.end());
        int res = 0;
        for (int i = 0; i < capacity.size() && additionalRocks >= capacity[i]; i++, res++)
            additionalRocks -= capacity[i];
        return res;
    }
};