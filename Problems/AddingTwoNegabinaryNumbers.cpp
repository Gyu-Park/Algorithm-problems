#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class AddingTwoNegabinaryNumbers
{
public:
    vector<int> addNegabinary(vector<int> &A, vector<int> &B)
    {
        vector<int> res;
        int carry = 0, i = A.size() - 1, j = B.size() - 1;
        while (i >= 0 || j >= 0 || carry)
        {
            if (i >= 0)
                carry += A[i--];
            if (j >= 0)
                carry += B[j--];
            res.push_back(carry & 1);
            carry = -(carry >> 1);
        }
        while (res.size() > 1 && res.back() == 0)
            res.pop_back();
        reverse(res.begin(), res.end());
        return res;
    }
};

int main(void)
{
    class AddingTwoNegabinaryNumbers obj;
    vector<int> A;
    vector<int> B;
    A = {1, 1, 1, 1, 1};
    B = {1, 0, 1};
    vector<int> res = obj.addNegabinary(A, B);
    for (int i : res)
    {
        cout << i << " ";
    }
    cout << endl;
    return 0;
}