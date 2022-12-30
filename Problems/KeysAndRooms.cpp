#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:  // iterative dfs solution
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size() - 1;
        vector<bool> visited(rooms.size(), false);
        queue<int> keys;
        for (int i = 0; i < rooms[0].size(); i++) {
            keys.push(rooms[0][i]);
        }
        visited[0] = true;
        while (!keys.empty()) {
            auto key = keys.front();
            keys.pop();
            if (visited[key])
                continue;
            visited[key] = true;
            n--;
            for (int i = 0; i < rooms[key].size(); i++) {
                if (!visited[rooms[key][i]])
                    keys.push(rooms[key][i]);
            }
        }
        return n == 0 ? true : false;
    }
};