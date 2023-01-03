#include <iostream>
#include <vector>
using namespace std;

class Solution {
   public:
    bool isInvalid(char c) {
        return c == '2' || c == '3' || c == '4' || c == '5' || c == '7';
    }
    bool isConfusing(char c1, char c2) {
        if ((c1 == '6' && c2 == '6') || (c1 == '9' && c2 == '9'))
            return true;
        else if ((c1 == '6' && c2 == '9') || (c1 == '9' && c2 == '6'))
            return false;
        return c1 != c2;
    }
    bool confusingNumber(int n) {
        string s = to_string(n);
        if (n < 10)
            return isConfusing(s[0], s[0]);
        int l = 0, r = s.size() - 1;
        bool res = false;
        while (l < r) {
            if (isInvalid(s[l]) || isInvalid(s[r]))
                return false;
            if (!res && isConfusing(s[l], s[r]))
                res = true;
            l++, r--;
        }
        if (l == r && isInvalid(s[l]))
            return false;
        return res;
    }

    bool confusingNumber2(int n) {
        string s1 = to_string(n);
        string s2 = "";
        for (int i = s1.size() - 1; i >= 0; i--) {
            if (isInvalid(s1[i]))
                return false;
            if (s1[i] == '9') {
                s2.push_back('6');
            } else if (s1[i] == '6') {
                s2.push_back('9');
            } else {
                s2.push_back(s1[i]);
            }
        }
        return s1 != s2;
    }
};