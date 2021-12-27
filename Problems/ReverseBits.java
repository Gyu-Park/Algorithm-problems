/**
 * Reverse bits of a given 32 bits unsigned integer.
 * 
 * Note:
 * 
 * Note that in some languages, such as Java, there is no unsigned integer type. 
 * In this case, both input and output will be given as a signed integer type. 
 * They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. 
 * Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 */
package Problems;

public class ReverseBits {
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result += n & 1;
            n >>= 1;
        }
        return result;
    }

    public static int reverseBits2(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;
            if (i < 31)
                result <<= 1;
        }
        return result;
    }

    public static int reverseBits3(int n) {
        int ret = n;
        ret = ret >>> 16 | ret << 16;
        ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
        ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
        ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
        ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
        return ret;
    }

    /**
     * The signed left shift operator "<<" shifts a bit pattern to the left.
     * 
     * The signed right shift operator ">>" shifts a bit pattern to the right.
     * 
     * The unsigned right shift operator ">>>" shifts a zero into the leftmost
     * position.
     * 
     * Bitwise OR (|)
     * The OR operator compares each binary digit of two integers and gives back 1
     * if either of them is 1.
     * 0110
     * 0101
     * -----
     * 0111
     * 
     * Bitwise AND (&)
     * The AND operator compares each binary digit of two integers and gives back 1
     * if both are 1, otherwise it returns 0.
     * 0110
     * 0101
     * -----
     * 0100
     * 
     * Bitwise XOR (^)
     * 
     * The XOR operator compares each binary digit of two integers and gives back 1
     * if both the compared bits are different.
     * 0110
     * 0101
     * -----
     * 0011
     */

    public static void main(String[] args) {
        int n = 43261596;
        System.out.println(reverseBits(n));
        System.out.println(reverseBits2(n));
        System.out.println(reverseBits3(n));
    }
}
