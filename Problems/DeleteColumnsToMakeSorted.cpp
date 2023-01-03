#include <iostream>
#include <vector>
using namespace std;

class Solution {
   public:
    int minDeletionSize(vector<string>& strs) {
        int res = 0;
        for (int col = 0; col < strs[0].size(); col++) {
            char pre = strs[0][col];
            for (int row = 1; row < strs.size(); row++) {
                if (strs[row][col] < pre) {
                    res++;
                    break;
                }
                pre = strs[row][col];
            }
        }
        return res;
    }
};