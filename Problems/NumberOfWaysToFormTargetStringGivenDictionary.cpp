#include <array>
#include <iostream>
#include <vector>
using namespace std;

class Solution {
   public:
    // time complexity: O(max(nm, nk))
    // space complexity: O(nk + (n * 26))
    int numWays(vector<string>& words, string target) {
        int words_n = words[0].size();
        int tar_n = target.size();

        // O(nm) where n is each word length in words and m is the number of words
        vector<array<long, 26>> cnt(words_n, array<long, 26>());
        for (int i = 0; i < words_n; i++) {
            for (int j = 0; j < words.size(); j++)
                cnt[i][words[j][i] - 'a']++;
        }

        // O(nk) where n is each word length in words and k is target length
        vector<vector<long>> dp(words_n, vector<long>(tar_n, 0));
        for (int i = 0; i < tar_n; i++) {
            for (int j = i; j < words_n; j++) {
                if (j > words_n - (tar_n - i))
                    break;
                long occur = cnt[j][target[i] - 'a'];
                if (i == 0)
                    dp[j][i] = j == 0 ? occur : occur + dp[j - 1][i];
                else
                    dp[j][i] = (occur * dp[j - 1][i - 1] + dp[j - 1][i]) % (1000000000 + 7);
            }
        }

        return dp[words_n - 1][tar_n - 1];
    }
};