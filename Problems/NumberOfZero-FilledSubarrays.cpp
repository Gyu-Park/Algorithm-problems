#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        // 000 00
        // 000
        // 00 * (2 + 1) 3
        // 0 *  (3 + 2) 5
        //      (4 + 3 + 1) 8
        //      (5 + 4 + 2) 11
        // first need to get max_occurence and store other zero occurences in a vector
        // and then loop through what we store in descending order and keep adding the number of sub arrays
        // while looping, we simply multuply the index by the number of subarrays.

        vector<int> arr(nums.size() + 1, 0);
        int max_occur = 0;
        int occur = 0;
        for (auto i : nums) {
            if (i == 0) {
                occur++;
            } else {
                arr[occur]++;
                max_occur = max(max_occur, occur);
                occur = 0;
            }
        }

        arr[occur]++;
        max_occur = max(max_occur, occur);

        int sub_arrays = 0;
        long long res = 0;
        for (int i = max_occur; i > 0; i--) {
            sub_arrays += arr[i];
            res += i * sub_arrays;
        }

        return res;
    }
};