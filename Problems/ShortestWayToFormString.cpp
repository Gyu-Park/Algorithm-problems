#include <bits/stdc++.h>
using namespace std;

class Solution {
   public:
    int shortestWay(string source, string target) {
        unordered_set<char> uset;
        for (auto c : source) {
            uset.insert(c);
        }

        int res = 0;
        int index = 0;
        for (int i = 0; i < target.size(); i++) {
            if (uset.find(target[i]) == uset.end())
                return -1;
            while (source[index++] != target[i]) {
                if (index >= source.size()) {
                    index = 0;
                    res++;
                }
            }
        }

        return ++res;
    }
};