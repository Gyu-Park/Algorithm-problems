#include <iostream>
#include <vector>
#include <set>
#include <queue>
using namespace std;

class Solution
{
public:
    int dist(vector<int> &worker, vector<int> &bike)
    {
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1]);
    }

    int assignBikes(vector<vector<int>> &workers, vector<vector<int>> &bikes)
    {
        priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> pq;
        pq.push(make_pair(0, make_pair(0, 0)));
        set<pair<int, int>> set;
        while (true)
        {
            pair<int, pair<int, int>> p = pq.top();
            pq.pop();
            int minCost = p.first;
            int index = p.second.first;
            int mask = p.second.second;
            if (set.find(p.second) != set.end())
                continue;
            set.insert(p.second);
            if (index == workers.size())
                return minCost;
            for (int i = 0; i < bikes.size(); i++)
            {
                if ((mask & (1 << i)) == 0)
                {
                    pair<int, pair<int, int>> newP;
                    newP.first = minCost + dist(workers[index], bikes[i]);
                    newP.second.first = index + 1;
                    newP.second.second = mask | (1 << i);
                    pq.push(newP);
                }
            }
        }
    }
};

int main(void)
{
    Solution s;
    vector<vector<int>> workers = {{0, 0}, {1, 1}, {2, 0}};
    vector<vector<int>> bikes = {{1, 0}, {2, 2}, {2, 1}};
    int res = s.assignBikes(workers, bikes);
    cout << res << endl;
    return 0;
}