#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution
{
public:
    int connectSticks(vector<int> &sticks)
    {
        priority_queue<int, vector<int>, greater<int>> pq(begin(sticks), end(sticks));
        int res = 0;
        while (pq.size() > 1)
        {
            int sum = pq.top();
            pq.pop();
            sum += pq.top();
            pq.pop();
            res += sum;
            pq.push(sum);
        }
        return res;
    }
};