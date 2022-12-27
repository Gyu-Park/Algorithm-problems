#include <iostream>
#include <vector>
#include <limits>
#include <queue>
using namespace std;

class Solution
{
public:
    // Bellman Ford's Algorithm
    // Let E be the number of flights and N be number of cities.
    // K is the limit of stops from source to destination.
    // time complexity: O((N + E) * K)
    int findCheapestPrice(int n, vector<vector<int>> &flights, int src, int dst, int k)
    {
        // src to dst at most k stops, n = vertices
        vector<int> dp(n, INT_MAX); // can use numeric_limits<int>::max() instead of INT_MAX
        dp[src] = 0;
        for (int i = 0; i < k + 1; i++)
        {
            // need to make a temp vector; otherwise, dp vector will be updated,
            // and it will calculate more than i stops as iterating through the flights
            vector<int> temp(dp);
            for (auto &flight : flights)
            {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                // compare with an element in dp vector
                if (dp[from] != INT_MAX)
                {
                    // change value in temp vector
                    temp[to] = min(temp[to], dp[from] + price);
                }
            }
            // now update dp vector as temp vector
            dp = temp;
        }
        return dp[dst] == INT_MAX ? -1 : dp[dst];
    }

    // bfs solution
    int findCheapestPrice2(int n, vector<vector<int>> &flights, int src, int dst, int k)
    {
        vector<vector<pair<int, int>>> adj(n);
        for (auto &flight : flights)
        {
            adj[flight[0]].push_back({flight[1], flight[2]});
        }

        vector<int> dist(n, numeric_limits<int>::max());
        queue<pair<int, int>> q;
        q.push({src, 0});
        int stops = 0;
        while (stops <= k && !q.empty())
        {
            int sz = q.size();
            while (sz--)
            {
                auto [node, distance] = q.front();
                q.pop();
                for (auto &[neighbor, price] : adj[node])
                {
                    if (price + distance >= dist[neighbor])
                        continue;
                    dist[neighbor] = price + distance;
                    q.push({neighbor, dist[neighbor]});
                }
            }
            stops++;
        }
        return dist[dst] == numeric_limits<int>::max() ? -1 : dist[dst];
    }

    // Dijkstra's Algorithm
    int findCheapestPrice3(int n, vector<vector<int>> &flights, int src, int dst, int k)
    {
        vector<vector<pair<int, int>>> adj(n);
        for (auto e : flights)
        {
            adj[e[0]].push_back({e[1], e[2]});
        }
        vector<int> stops(n, numeric_limits<int>::max());
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;
        // {dist_from_src_node, node, number_of_stops_from_src_node}
        pq.push({0, src, 0});

        while (!pq.empty())
        {
            auto temp = pq.top();
            pq.pop();
            int dist = temp[0];
            int node = temp[1];
            int steps = temp[2];
            // We have already encountered a path with a lower cost and fewer stops,
            // or the number of stops exceeds the limit.
            if (steps > stops[node] || steps > k + 1)
                continue;
            stops[node] = steps;
            if (node == dst)
                return dist;
            for (auto &[neighbor, price] : adj[node])
            {
                pq.push({dist + price, neighbor, steps + 1});
            }
        }
        return -1;
    }
};