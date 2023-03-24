#include <bits/stdc++.h>
using namespace std;

class Solution {
   public:
    string longestPalindrome(string s) {
        // bottom-up dp
        // "abbba"
        // we need 5x5  table
        // i = right pointer, j = left pointer
        // since it's bottom-up dp, we solve it from the bottom
        // which is the length 1 string to s.size().

        // an example for above string,
        //   a b b b a
        // a T F F F T
        // b   T T T F
        // b     T T F
        // b       T F
        // a         T

        int maxLen = 0;
        int start = 0;
        int n = s.size();
        vector<vector<int>> dp(n, vector<int>(n, false));
        for (int k = 0; k < n; k++) {
            for (int j = 0, i = k; i < n; i++, j++) {
                if (j == i)
                    dp[j][i] = true;
                else if (i - j == 1)
                    dp[j][i] = s[i] == s[j] ? true : false;
                else
                    dp[j][i] = dp[j + 1][i - 1] && s[j] == s[i] ? true : false;

                if (dp[j][i] && maxLen < i - j + 1) {
                    maxLen = i - j + 1;
                    start = j;
                }
            }
        }

        return s.substr(start, maxLen);
    }

    // another solution
    string longestPalindrome(string s) {
        if (s.size() < 2) return s;
        int max_len = 0;
        int start_idx = 0;
        int i = 0;
        while (i < s.size()) {
            int r_ptr = i;
            int l_ptr = i;
            // find the middle of a palindrome
            while (r_ptr < s.size() - 1 && s[r_ptr] == s[r_ptr + 1]) r_ptr++;
            i = r_ptr + 1;
            // expand from the middle out
            while (r_ptr < s.size() - 1 && l_ptr > 0 && s[r_ptr + 1] == s[l_ptr - 1]) {
                r_ptr++;
                l_ptr--;
            }
            int new_len = r_ptr - l_ptr + 1;
            if (new_len > max_len) {
                start_idx = l_ptr;
                max_len = new_len;
            }
        }
        return s.substr(start_idx, max_len);
    }
};