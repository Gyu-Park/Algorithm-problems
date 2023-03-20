#include <deque>
#include <iostream>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <vector>
using namespace std;

class Solution {
   public:
    int dfs(vector<int>& favorite, unordered_set<int>& visited, deque<int>& q, int emp) {
        visited.insert(emp);
        q.push_back(emp);
        if (visited.find(favorite[emp]) != visited.end()) {
            while (!q.empty() && q.front() != favorite[emp]) {
                q.pop_front();
            }

            return q.size();
        }

        return dfs(favorite, visited, q, favorite[emp]);
    }

    int findLongestTail(deque<int>& q, unordered_map<int, vector<int>>& indegree, unordered_set<int>& visited) {
        int ret = 0;
        unordered_set<int> cycle(q.begin(), q.end());
        while (!q.empty()) {
            int v = q.front();
            q.pop_front();
            queue<int> qq;
            for (int i = 0; i < indegree[v].size(); i++) {
                if (cycle.find(indegree[v][i]) == cycle.end())
                    qq.push(indegree[v][i]);
            }

            queue<int> qqq;
            int level = 0;
            while (!qq.empty()) {
                int ver = qq.front();
                qq.pop();
                visited.insert(ver);
                for (int i = 0; i < indegree[ver].size(); i++) {
                    qqq.push(indegree[ver][i]);
                }

                if (qq.size() == 0) {
                    level++;
                    while (!qqq.empty()) {
                        qq.push(qqq.front());
                        qqq.pop();
                    }
                }
            }
            ret += level;
        }
        return ret;
    }

    // another solution
    int maximumInvitations(vector<int>& favorite) {
        // first look for cycle using dfs and queue for the size of the cycle
        // if cycle has more than 2 vertices, then return it
        // if cycle has only 2 vertices, find the longest tail from either the two vertices

        int res = 0;
        int n = favorite.size();
        unordered_map<int, vector<int>> indegree;
        for (int i = 0; i < n; i++) {
            indegree[favorite[i]].push_back(i);
        }

        unordered_set<int> visited;
        deque<int> q;
        int coll_cycle_2 = 0;
        for (int i = 0; i < n; i++) {
            if (visited.find(i) != visited.end())
                continue;
            int cycle = dfs(favorite, visited, q, i);
            if (cycle == 2) {
                cycle += findLongestTail(q, indegree, visited);
                coll_cycle_2 += cycle;
                res = max(res, coll_cycle_2);
            } else
                res = max(res, cycle);
            deque<int> empty;
            swap(empty, q);
        }

        return res;
    }
};

const int mxN = 100;
class anotherSolution {
    // topological sort + union find
   public:
    int inDeg[mxN];
    int ans[mxN];
    int sz[mxN];
    int un[mxN];

    int find(int a) {
        if (un[a] == a)
            return a;
        else
            return un[a] = find(un[a]);
    }
    void uni(int a, int b) {
        int x = find(a), y = find(b);
        if (x == y) return;
        if (sz[x] < sz[y]) swap(x, y);
        // x has more, y has less
        sz[x] += sz[y];
        un[y] = un[x];
    }

    int maximumInvitations(vector<int>& adj) {
        int N = adj.size();

        for (int i = 0; i < N; ++i) {
            sz[i] = 1;
            un[i] = i;
            ans[i] = 1;
        }

        for (int i = 0; i < N; ++i) {
            inDeg[adj[i]]++;
        }
        // start of topo sort
        queue<int> st;
        for (int i = 0; i < N; ++i) {
            if (inDeg[i] == 0) {
                st.push(i);
            }
        }
        while (!st.empty()) {
            int v = st.front();
            st.pop();
            int c = adj[v];
            ans[c] = max(ans[c], ans[v] + 1);  // largest possible non-cyclic path up to node 'c'
            inDeg[c]--;
            if (inDeg[c] == 0) {
                st.push(c);
            }
        }
        // end of topo sort

        // figuring out the lengths of the cycles with disjoint structure
        for (int i = 0; i < N; ++i) {
            if (inDeg[i])
                uni(i, adj[i]);
        }

        int res = 0;
        int joinable = 0;
        for (int i = 0; i < N; ++i) {
            if (!inDeg[i]) continue;  // we only care about the cyclic components
            int len = sz[find(i)];    // length of cycle for current node
            if (len == 2) {
                int neigh = adj[i];
                inDeg[neigh]--;             // making sure we dont overcount in our "joinable" sum by revisiting neighbor
                len = ans[i] + ans[neigh];  // answer for cycle of length 2 is the sum of the largest paths up to the 2 nodes
                joinable += len;
            } else {
                // the only answer for a node with cycle of length >= 3 is just the length
                res = max(res, len);
            }
        }

        return max(res, joinable);
    }
};