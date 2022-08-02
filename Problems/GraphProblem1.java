package Problems;

public class GraphProblem1 {
    static int[][] solution(boolean[][] field, int x, int y) {
        int[][] record = new int[field.length][field[0].length];
        int[][] res = new int[field.length][field[0].length];
        for (int i = 0; i < field.length; i ++) {
            for (int j = 0; j < field[0].length; j++) {
                res[i][j] = -1;
                if (field[i][j]) {
                    record = helper1(record, i, j);
                }
            }
        }
        
        res = helper2(res, record, x, y);
        return res;
    }
    
    static int [][] helper1(int[][] record, int i , int j) {
        for (int ii = i - 1; ii <= i + 1; ii++) {
            for (int jj = j - 1; jj <= j + 1; jj++) {
                if (ii < 0 || jj < 0 || ii > record.length-1 || jj > record[0].length-1)
                    continue;
                if (ii == i && jj == j)
                    continue;
                record[ii][jj]++;
            }
        }
        return record;
    }
    
    static int [][] helper2(int[][] res, int[][] record, int x , int y) {
        if (record[x][y] == 0) {
            for (int ii = x - 1; ii <= x + 1; ii++) {
                for (int jj = y - 1; jj <= y + 1; jj++) {
                    if (ii < 0 || jj < 0 || ii > record.length-1 || jj > record[0].length-1)
                        continue;
                    if (res[ii][jj] == -1)
                        res[ii][jj] = record[ii][jj];
                    if (res[ii][jj] == 0)
                        res = helper2(res, record, ii, jj);
                }
            }
        } else {
            res[x][y] = record[x][y];
        }
        return res;
    }

    public static void main(String[] args) {
        boolean[][] field = {{false, true, true},
                        {true, false, true},
                        {false, false, true}};
        System.out.println(solution(field, 1, 1));
    }
    
}
