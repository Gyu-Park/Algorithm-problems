#include <iostream>
#include <unordered_map>
using namespace std;

class Solution
{
public:
    int lengthOfLongestSubstringKDistinct(string s, int k)
    {
        unordered_map<char, int> umap;
        int res = 0, low = -1;
        for (int i = 0; i < s.size(); i++)
        {
            ++umap[s[i]];
            while (umap.size() > k)
            {
                if (--umap[s[++low]] == 0)
                    umap.erase(s[low]);
            }
            res = max(res, i - low);
        }
        return res;
    }
};