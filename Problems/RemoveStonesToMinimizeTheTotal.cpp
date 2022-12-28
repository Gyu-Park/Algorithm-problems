#include <iostream>
#include <numeric>
#include <queue>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using maxheap
    int minStoneSum(vector<int>& piles, int k) {
        priority_queue<unsigned int> pq(piles.begin(), piles.end());
        unsigned int sum = accumulate(piles.begin(), piles.end(), 0);
        while (k-- > 0 && sum > 1) {
            unsigned int i = pq.top();
            pq.pop();
            unsigned int j = i / 2;
            i -= j;
            sum -= j;
            pq.push(i);
        }
        return sum;
    }
};