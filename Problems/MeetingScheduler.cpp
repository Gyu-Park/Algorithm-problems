#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution
{
public:
    vector<int> minAvailableDuration(vector<vector<int>> &slots1, vector<vector<int>> &slots2, int duration)
    {
        sort(slots1.begin(), slots1.end());
        sort(slots2.begin(), slots2.end());
        int count1 = 0, count2 = 0;
        while (slots1.size() > count1 && slots2.size() > count2)
        {
            vector<int> slot1 = slots1[count1], slot2 = slots2[count2];
            int dur = min(slot1[1], slot2[1]) - max(slot1[0], slot2[0]);
            int start = max(slot1[0], slot2[0]);
            if (dur >= duration)
                return {start, start + duration};
            if (slot2[1] > slot1[1])
                count1++;
            else
                count2++;
        }
        return vector<int>();
    }
};