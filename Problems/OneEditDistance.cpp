#include <iostream>
using namespace std;

class Solution
{
public:
    bool isOneEditDistance(string s, string t)
    {
        int ns = s.size();
        int nt = t.size();
        // ensure nt is longer string
        if (ns > nt)
            return isOneEditDistance(t, s);
        if (ns + 1 < nt || s == t)
            return 0;
        int i;
        for (i = 0; i < ns; i++)
        {
            char cs = s.at(i);
            char ct = t.at(i);
            if (cs != ct && ns == nt)
            {
                s.erase(i, 1);
                t.erase(i, 1);
                return s == t;
            }
            else if (cs != ct && ns != nt)
            {
                string tTemp = t;
                tTemp.erase(i, 1);
                if (s == tTemp)
                    return 1;
            }
        }
        t.erase(i, 1);
        return s == t;
    }

    bool isOneEditDistance2(string s, string t)
    {
        int ns = s.size();
        int nt = t.size();
        // ensure nt is longer
        if (ns > nt)
            return isOneEditDistance(t, s);
        for (int i = 0; i < ns; i++)
        {
            if (s[i] != t[i])
            {
                if (ns == nt)
                    return s.substr(i + 1) == t.substr(i + 1);
                return s.substr(i) == t.substr(i + 1);
            }
        }
        return ns + 1 == nt;
    }
};