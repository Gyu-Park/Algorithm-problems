/**
 * Alice has n candies, where the ith candy is of type candyType[i]. 
 * Alice noticed that she started to gain weight, so she visited a doctor.
 * 
 * The doctor advised Alice to only eat n / 2 of the candies she has (n is always even). 
 * Alice likes her candies very much, and she wants to eat the maximum number of different types of candies 
 * while still following the doctor's advice.
 * 
 * Given the integer array candyType of length n, 
 * return the maximum number of different types of candies she can eat if she only eats n / 2 of them.
 */
package Problems;

import java.util.*;

public class DistributeCandies {
    public static int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        set.add(candyType[0]);
        int halfNum = candyType.length / 2;
        for (int i = 1; i < candyType.length; i++) {
                set.add(candyType[i]);
        }
        return set.size() < halfNum ? set.size() : halfNum;
    }

    // solution without a hash set
    public static int anotherDistributeCandies(int[] candyType) {
        Arrays.sort(candyType);
        int typeNum = 1;
        int halfNum = candyType.length / 2;
        for (int i = 1; i < candyType.length; i++) {
            if (candyType[i - 1] != candyType[i]) {
                typeNum++;
            }
        }
        return typeNum < halfNum ? typeNum : halfNum;
    }
    
    public static void main(String[] args) {
        int[] candyType = {1, 1, 2, 2, 3, 3};
        System.out.println(distributeCandies(candyType));
    }
}
