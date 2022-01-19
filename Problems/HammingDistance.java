/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * 
 * Given two integers x and y, return the Hamming distance between them.
 */
package Problems;

public class HammingDistance {
    public static int hammingDistance(int x, int y) {
        x ^= y;
        String s = Integer.toBinaryString(x);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                res++;
        }
        return res;
    }

    // better solution
    public int anotherHammingDistance(int x, int y) {
        int xor = x ^ y;
        int res = 0;
        while (xor != 0) {
            res += xor & 1;
            xor >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        System.out.println(hammingDistance(x, y));
    }
}
