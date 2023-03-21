#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:
    int closestMeetingNode(vector<int>& edges, int node1, int node2) {
        vector<int> visited1(edges.size(), 0);
        vector<int> visited2(edges.size(), 0);
        vector<int> dist1(edges.size(), 0);
        vector<int> dist2(edges.size(), 0);
        queue<int> q;
        q.push(node1);
        int dist = 0;
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            if (node == -1) {
                continue;
            }
            if (visited1[node] == 1)
                break;
            visited1[node] = 1;
            dist1[node] = dist++;
            q.push(edges[node]);
        }

        dist = 0;
        q.push(node2);
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            if (node == -1) {
                continue;
            }
            if (visited2[node] == 1) {
                break;
            }
            visited2[node] = 1;
            dist2[node] = dist++;
            q.push(edges[node]);
        }

        int minDist = INT_MAX;
        int res = -1;
        for (int i = 0; i < edges.size(); i++) {
            if (visited1[i] == 1 && visited2[i] == 1 && minDist > max(dist1[i], dist2[i])) {
                minDist = max(dist1[i], dist2[i]);
                res = i;
            }
        }

        return res;
    }
};