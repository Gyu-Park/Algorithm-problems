/**
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. 
 * All the scores are guaranteed to be unique.
 * 
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score, 
 * the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 * 
 * The 1st place athlete's rank is "Gold Medal".
 * The 2nd place athlete's rank is "Silver Medal".
 * The 3rd place athlete's rank is "Bronze Medal".
 * For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 */
package Problems;

import java.util.*;

public class RelativeRanks {
    // using HashMap
    public static String[] findRelativeRanks(int[] score) {
        int[] sortedScore = Arrays.copyOf(score, score.length);
        Arrays.sort(sortedScore);
        Map<Integer, String> map = new HashMap<>();
        int rank = 4;
        for (int i = sortedScore.length - 1; i >= 0; i--) {
            if (i == sortedScore.length - 1) {
                map.put(sortedScore[i], "Gold Medal");
            } else if (i == sortedScore.length - 2) {
                map.put(sortedScore[i], "Silver Medal");
            } else if (i == sortedScore.length - 3) {
                map.put(sortedScore[i], "Bronze Medal");
            } else {
                map.put(sortedScore[i], Integer.toString(rank++));
            }
        }

        String[] res = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            res[i] = map.get(score[i]);
        }
        return res;
    }

    // another solution using TreeMap
    public static String[] anotherFindRelativeRanks(int[] score) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i], i);
        }
        int i = 0;
        String[] sol = new String[score.length];
        for (Integer val : map.values()) {
            if (i == score.length - 3) {
                sol[val] = "Bronze Medal";
            } else if (i == score.length - 2) {
                sol[val] = "Silver Medal";
            } else if (i == score.length - 1) {
                sol[val] = "Gold Medal";
            } else {
                sol[val] = score.length - i + "";
            }
            i++;
        }
        return sol;
    }

    // fastest solution with counting sort algorithm
    public static String[] fastestFindRelativeRanks(int[] score) {
        String[] result = new String[score.length];
        int max = 0;
        for (int i : score) {
            if (i > max)
                max = i;
        }
        int[] hash = new int[max + 1];
        for (int i = 0; i < score.length; i++) {
            hash[score[i]] = i + 1;
        }
        int place = 1;
        for (int i = hash.length - 1; i >= 0; i--) {
            if (hash[i] != 0) {
                if (place == 1) {
                    result[hash[i] - 1] = "Gold Medal";
                } else if (place == 2) {
                    result[hash[i] - 1] = "Silver Medal";
                } else if (place == 3) {
                    result[hash[i] - 1] = "Bronze Medal";
                } else {
                    result[hash[i] - 1] = String.valueOf(place);
                }
                place++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] score = { 10, 3, 8, 9, 4 };
        String[] res = findRelativeRanks(score);
        for (String s : res) {
            System.out.print(s + " ");
        }

        System.out.println(" ");

        String[] res2 = anotherFindRelativeRanks(score);
        for (String s : res2) {
            System.out.print(s + " ");
        }

        System.out.println(" ");

        String[] res3 = fastestFindRelativeRanks(score);
        for (String s : res3) {
            System.out.print(s + " ");
        }
    }
}
