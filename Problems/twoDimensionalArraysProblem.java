package Problems;

import java.util.*;

public class twoDimensionalArraysProblem {
    static int[][] solution(int[][] a) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        HashMap<Double, Integer> map = new HashMap<>();
        
        double mean = 0;
        int index = 0;
        
        for (int i = 0; i < a.length; i ++) {
            mean = Arrays.stream(a[i]).average().getAsDouble();
            if (map.get(mean) != null) {
                res.get(map.get(mean)).add(i);
            } else {
                map.put(mean, index++);
                res.add(new ArrayList<Integer>());
                res.get(map.get(mean)).add(i);
            }
        }
        
        int[][] ress = res.stream().map(u -> u.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
        return ress;
    }
    

    public static void main(String[] args) {
        int[][] a = {{3, 3, 2, 2}, {4, 4}, {1, 4}, {3, 3, 3}};
        int[][] res = solution(a);
        for (int[] array : res) {
            for (int i : array) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
