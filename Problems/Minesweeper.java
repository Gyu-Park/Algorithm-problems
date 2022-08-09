package Problems;

import java.util.Arrays;

public class Minesweeper {
    static int[][] solution(boolean[][] field, int x, int y) {
        int[][] res = new int[field.length][field[0].length];
        for (int[] row : res)
            Arrays.fill(row, -1);
        boolean[][] isVisited = new boolean[field.length][field[0].length];
        bfs(field, res, x, y, isVisited);
        return res;
    }
    
    static void bfs(boolean[][] field, int[][] res, int i, int j, boolean[][] isVisited) {
        if (i < 0 || i > field.length - 1)
            return;
        if (j < 0 || j > field[0].length - 1)
            return;
        if (isVisited[i][j])
            return;
        isVisited[i][j] = true;
        
        res[i][j] = 0;
        for (int ii = i - 1; ii <= i + 1; ii++) {
            for (int jj = j - 1; jj <= j + 1; jj++) {
                if (ii == i && jj == j)
                    continue;
                res[i][j] += isBomb(field, ii, jj);
            }
        }
        
        if (res[i][j] > 0)
            return;
        
        for (int ii = i - 1; ii <= i + 1; ii++) {
            for (int jj = j - 1; jj <= j + 1; jj++) {
                if (ii == i && jj == j)
                    continue;
                bfs(field, res, ii, jj, isVisited);
            }
        }
    }
    
    static int isBomb(boolean[][] field, int i, int j) {
        if (i < 0 || i > field.length - 1)
            return 0;
        if (j < 0 || j > field[0].length - 1)
            return 0;
        if (field[i][j])
            return 1;
        else
            return 0;
    }
    
    public static void main(String[] args) {
        int x = 3;
        int y = 2;
        boolean[][] field = { {true,false,true,true,false}, 
                              {true,false,false,false,false}, 
                              {false,false,false,false,false}, 
                              {true,false,false,false,false} };
        System.out.println(solution(field, x, y));
    }    
}
