/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), 
 * ans[i] is the number of 1's in the binary representation of i.
 */
package Problems;

public class CountingBits {
    public static int[] countBits(int n) {
        int[] nums = new int[n + 1];
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            st.append(Integer.toBinaryString(i));
            int oneCount = 0;
            for (int j = 0; j < st.length(); j++) {
                if (st.charAt(j) == '1')
                    oneCount++;
            }
            nums[i] = oneCount;
            st.delete(0, st.length());
        }
        return nums;
    }

    // when even >> 1, it doesn't change the number of "1s".
    // when odd >> 1, it gets rid of one "1".
    public int[] betterCountBits(int n) {
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++)
            nums[i] = nums[i >> 1] + (i & 1);
        return nums;
    }

    public static void main(String[] args) {
        int n = 8;
        int[] nums = countBits(n);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
