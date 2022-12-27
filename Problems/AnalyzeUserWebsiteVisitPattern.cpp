#include <iostream>
#include <vector>
#include <unordered_map>
#include <map>
#include <unordered_set>
using namespace std;

class Solution
{
public:
    // hard problem... u.u
    vector<string> mostVisitedPattern(vector<string> &username, vector<int> &timestamp, vector<string> &website)
    {
        // umap to store username, time, and website respectively
        unordered_map<string, map<int, string>> m;
        // umap to count occurrence of patterns
        unordered_map<string, int> cnt;
        // int to count maximum number of occurrence
        int m_cnt = 0;
        string res;
        // store username, time, and website respectively
        for (auto i = 0; i < username.size(); ++i)
            m[username[i]][timestamp[i]] = website[i];
        // iterate through m, which u represents each user
        for (auto u : m)
        {
            // uset to filter out the same pattern
            unordered_set<string> ts;
            // build pattern and put it into the uset
            for (auto it = begin(u.second); it != end(u.second); ++it)
                for (auto it1 = next(it); it1 != end(u.second); ++it1)
                    for (auto it2 = next(it1); it2 != end(u.second); ++it2)
                        ts.insert(it->second + "$" + it1->second + "#" + it2->second);
            // count the occurrence of pattern in uset
            for (auto s : ts)
                ++cnt[s];
        }
        // iterate through occurrence of patterns
        for (auto t : cnt)
        {
            // if the number of occurrence is the same as or greater than previous value
            if (t.second >= m_cnt)
            {
                // if it is the first iteration or previous value is less than current occurrence, res = t.first;
                // otherwise, we need to pick the lexicographically smaller one between them
                res = res == "" || m_cnt < t.second ? t.first : min(res, t.first);
                m_cnt = t.second;
            }
        }
        // removes separator $ and #, and put each element into vector to return
        auto p1 = res.find("$"), p2 = res.find("#");
        return {res.substr(0, p1), res.substr(p1 + 1, p2 - p1 - 1), res.substr(p2 + 1)};
    }
};