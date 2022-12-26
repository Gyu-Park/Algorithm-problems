#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution
{
public:
    vector<int> answerQueries(vector<int> &nums, vector<int> &queries)
    {
        sort(nums.begin(), nums.end());
        for (int i = 1; i < nums.size(); i++)
        {
            nums[i] = nums[i - 1] + nums[i];
        }

        vector<int> res;
        for (auto query : queries)
        {
            int index = upper_bound(nums.begin(), nums.end(), query) - nums.begin();
            res.push_back(index);
        }

        return res;
    }
};

int main(void)
{
    Solution s;
    vector<int> nums = {4, 5, 2, 1};
    vector<int> queries = {3, 10, 21};
    vector<int> res = s.answerQueries(nums, queries);
    for (int i : res)
    {
        cout << i << endl;
    }
    return 0;
}