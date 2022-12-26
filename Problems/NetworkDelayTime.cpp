#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution
{
public:
    vector<pair<int, int>> adj[101];

    void dijkstra(vector<int> &table, int source, int n)
    {
        // initialize priority queue
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        // push initial pair; the time from curNode to curNode = 0;
        pq.push({0, source});

        // key in table is destination from k node
        // value in table is time from k to dest
        table[source] = 0;

        // update path
        while (!pq.empty())
        {
            int curNodeTime = pq.top().first;
            int curNode = pq.top().second;
            pq.pop();

            // if curNodeTime is greater than time to dest,
            // don't need to update, so continue to next iteration
            if (curNodeTime > table[curNode])
                continue;

            // need to look at adjacent nodes from dest to update path
            for (pair<int, int> edge : adj[curNode])
            {
                int time = edge.first;
                int neighborNode = edge.second;

                // so if curNodeTime + time is less than neighborNode's value in table,
                // update it as curNodeTime + time and push it into pq.
                // ex) A -> B -> C is shorter path than direct path A to C.
                if (table[neighborNode] > curNodeTime + time)
                {
                    table[neighborNode] = curNodeTime + time;
                    pq.push({table[neighborNode], neighborNode});
                }
            }
        }
    }

    int networkDelayTime(vector<vector<int>> &times, int n, int k)
    {
        for (vector<int> time : times)
        {
            int source = time[0];
            int dest = time[1];
            int t = time[2];
            adj[source].push_back({t, dest});
        }

        vector<int> table(n + 1, INT_MAX);
        dijkstra(table, k, n);

        int res = INT_MIN;
        for (int i = 1; i <= n; i++)
        {
            res = max(res, table[i]);
        }
        return res == INT_MAX ? -1 : res;
    }

    // another solution using bfs
    int networkDelayTime2(vector<vector<int>> &times, int n, int k)
    {
        for (vector<int> time : times)
        {
            int source = time[0];
            int dest = time[1];
            int t = time[2];
            adj[source].push_back({t, dest});
        }

        queue<int> q;
        q.push(k);

        vector<int> table(n + 1, INT_MAX);
        table[k] = 0;

        while (!q.empty())
        {
            int curNode = q.front();
            q.pop();
            for (pair<int, int> p : adj[curNode])
            {
                int time = p.first;
                int dest = p.second;
                if (table[dest] > table[curNode] + time)
                {
                    table[dest] = table[curNode] + time;
                    q.push(dest);
                }
            }
        }

        int res = INT_MIN;
        for (int i = 1; i <= n; i++)
        {
            res = max(res, table[i]);
        }
        return res == INT_MAX ? -1 : res;
    }
};