#include <iostream>
#include <stack>
#include <vector>
using namespace std;

class Solution {
public:
    int sumSubarrayMins(vector<int>& arr) {
        int mod = 1e9 + 7;
        int mid;
        int prev;
        long res = 0;
        long sum;
        stack<int> stack;

        for (int i = 0; i <= arr.size(); i++) {
            while (!stack.empty() && (i == arr.size() || arr[stack.top()] > arr[i])) {
                mid = stack.top(); stack.pop();
                if (stack.empty())
                    prev = -1;
                else
                    prev = stack.top();
                res += (long)arr[mid] * (i - mid) * (mid - prev) % mod;
                res %= mod;
            }
            stack.push(i);
        }

        return (int)res;
    }
};