/**
 * A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself. 
 * A divisor of an integer x is an integer that can divide x evenly.
 * 
 * Given an integer n, return true if n is a perfect number, otherwise return false.
 */
package Problems;

public class PerfectNumber {
    public static boolean checkPerfectNumber(int num) {
        if (num == 1)
            return false;
        int sum = 1;
        double sqrt = Math.sqrt(num);
        for (int i = 2; i < sqrt; i++) {
            if (num % i == 0) {
                sum += i + (num / i);
            }
        }
        return num == sum;
    }
    
    public static void main(String[] args) {
        int num = 28;
        System.out.println(checkPerfectNumber(num));
    }
}
