#include <bits/stdc++.h>
using namespace std;

class Solution {
   public:
    // build subsequence with binary search
    // time complexity: O(nlogn)
    // space complexity: O(n)
    void replace(vector<int>& sub, int num) {
        if (sub[0] > num) {
            sub[0] = num;
            return;
        }

        int start = 0;
        int end = sub.size() - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (sub[mid] == num || (sub[mid] > num && sub[mid - 1] < num)) {
                sub[mid] = num;
                return;
            }
            if (sub[mid] > num) {
                end = mid;
            } else if (sub[mid] < num) {
                start = mid + 1;
            }
        }
    }

    int lengthOfLIS(vector<int>& nums) {
        vector<int> sub;
        for (auto num : nums) {
            if (sub.size() == 0 || num > sub[sub.size() - 1])
                sub.push_back(num);
            else if (sub[sub.size() - 1] == num)
                continue;
            else if (num < sub[sub.size() - 1])
                replace(sub, num);
        }
        return sub.size();
    }
};