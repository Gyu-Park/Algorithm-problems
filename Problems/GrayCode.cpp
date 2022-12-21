#include <iostream>
#include <vector>
#include <unordered_set>
#include <bitset>
using namespace std;

class Solution
{
    // iterative solution
public:
    vector<int> grayCode(int n)
    {
        unordered_set<int> uset;
        vector<int> res;
        res.push_back(0);
        uset.insert(0);
        while (res.size() != (1 << n))
        {
            int prev = res.at(res.size() - 1);
            for (int i = 0; i < n; i++)
            {
                int candidate = prev ^ (1 << i);
                if (uset.find(candidate) == uset.end())
                {
                    res.push_back(candidate);
                    uset.insert(candidate);
                    break;
                }
            }
        }
        return res;
    }

    // recursive backtracking solution using bitset
    void utils(bitset<32> &bits, vector<int> &result, int k)
    {
        if (k == 0)
        {
            result.push_back(bits.to_ulong());
        }
        else
        {
            utils(bits, result, k - 1);
            bits.flip(k - 1);
            utils(bits, result, k - 1);
        }
    }

public:
    vector<int> grayCode(int n)
    {
        bitset<32> bits;
        vector<int> result;
        utils(bits, result, n);
        return result;
    }
};