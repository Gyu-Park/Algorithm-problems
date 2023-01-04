#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:
    int minimumRounds(vector<int>& tasks) {
        unordered_map<int, int> umap;
        for (auto& i : tasks) {
            umap[i]++;
        }

        int res = 0;
        for (auto& [key, val] : umap) {
            if (val == 1)
                return -1;
            int remainder = val % 3;
            int quotient = val / 3;
            if (remainder == 0) {
                res += quotient;
            } else {
                res += quotient + 1;
            }
        }
        return res;
    }
};