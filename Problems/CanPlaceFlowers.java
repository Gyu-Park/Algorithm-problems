/**
 * You have a long flowerbed in which some of the plots are planted, and some are not. 
 * However, flowers cannot be planted in adjacent plots.
 * 
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, 
 * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 */
package Problems;

public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

    }

    public static void main(String[] args) {
        int[] flowerbed = { 1, 0, 0, 0, 1 };
        int n = 1;
        System.out.println(canPlaceFlowers(flowerbed, n));
    }
}
