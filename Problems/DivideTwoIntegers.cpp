#include <iostream>
#include <limits.h>
#include <vector>

using namespace std;

class Solution {
    int HALF_INT_MIN = -1073741824;
public:
    int divide(int dividend, int divisor) {
        // special case: overflow
        if (dividend == INT_MIN && divisor == -1)
            return INT_MAX;
        
        int negative = 2;
        if (dividend > 0) {
            negative--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negative--;
            divisor = -divisor;
        }

        vector<int> doubles;
        vector<int> powersOfTwo;

        int powerOfTwo = -1;
        while (divisor >= dividend) {
            doubles.push_back(divisor);
            powersOfTwo.push_back(powerOfTwo);
            // Prevent needless overflows from occurring...
            if (divisor < HALF_INT_MIN)
                break;
            divisor += divisor;
            powerOfTwo += powerOfTwo;
        }

        int quotient = 0;
        /* Go from largest double to smallest, checking if the current double fits.
         * into the remainder of the dividend */
        for (int i = doubles.size() - 1; i >= 0; i--) {
            if (doubles[i] >= dividend) {
                // If it does fit, add the current powerOfTwo to the quotient.
                quotient += powersOfTwo[i];
                // Update dividend to take into account the bit we've now removed.
                dividend -= doubles[i];
            }
        }

        if (negative != 1)
            return -quotient;
        return quotient;
    }
};

int main(void)
{
    class Solution obj;
    int dividend = 2000;
    int divisor = -3;
    cout << obj.divide(dividend, divisor);
    cout << endl;
    return 0;
}