#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

class EqualSumArraysWithMinimumNumberOfOperations
{
public:
    int minOperations(vector<int> &nums1, vector<int> &nums2)
    {
        if (nums2.size() * 6 < nums1.size() || nums1.size() * 6 < nums2.size())
            return -1;
        int sum1 = accumulate(begin(nums1), end(nums1), 0), sum2 = accumulate(begin(nums2), end(nums2), 0);
        if (sum1 > sum2)
            swap(nums1, nums2);
        int cnt[6] = {}, diff = abs(sum1 - sum2), res = 0;
        for (auto n : nums1)
            ++cnt[6 - n];
        for (auto n : nums2)
            ++cnt[n - 1];
        for (int i = 5; i > 0 && diff > 0; --i)
        {
            int take = min(cnt[i], diff / i + (diff % i != 0));
            diff -= take * i;
            res += take;
        }
        return res;
    }
};

int main(void)
{
    class EqualSumArraysWithMinimumNumberOfOperations obj;
    vector<int> a = {1, 2, 3, 4, 5, 6};
    vector<int> b = {1, 1, 2, 2, 2, 2};
    cout << obj.minOperations(a, b) << endl;
}