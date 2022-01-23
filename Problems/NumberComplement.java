/**
 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
 * 
 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
 * Given an integer num, return its complement.
 */
package Problems;

public class NumberComplement {
    public static int findComplement(int num) {
        int n = 0;
        while (n < num) {
            n = (n << 1) | 1;
        }
        return n ^ num;
    }

    public static int anotherFindComplement(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        return num ^ mask;
    }

    public static void main(String[] args) {
        int num = 5;
        System.out.println(findComplement(num));
        System.out.println(anotherFindComplement(num));
    }
}
