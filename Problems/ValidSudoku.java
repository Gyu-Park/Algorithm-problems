/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated 
 * according to the following rules:
 * 
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * 
 * Note:
 * 
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 */
package Problems;

import java.util.*;

public class ValidSudoku {
    static boolean isDuplicate = false;
    public static boolean isValidSudoku(char[][] board) {
        Map<Character, List<Set<Integer>>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.')
                    continue;
                if (map.containsKey(board[i][j])) {
                    helper(map.get(board[i][j]) ,i, j);
                    if (isDuplicate)
                        return false;
                } else {
                    List<Set<Integer>> list = new ArrayList<>();
                    helper(list, i, j);
                    map.put(board[i][j], list);
                }
            }
        }

        return true;
    }

    private static void helper(List<Set<Integer>> list, int i, int j) {
        int block = whichBlock(i, j);
        if (list.isEmpty()) {
            Set<Integer> square = new HashSet<>();
            Set<Integer> horiz = new HashSet<>();
            Set<Integer> verti = new HashSet<>();
            horiz.add(i);
            verti.add(j);
            square.add(block);
            list.add(square);
            list.add(horiz);
            list.add(verti);
        } else {
            if (list.get(0).contains(block) || list.get(1).contains(i) || list.get(2).contains(j)) {
                isDuplicate = true;
            } else {
                list.get(0).add(block);
                list.get(1).add(i);
                list.get(2).add(j);
            }
        }
    }

    private static int whichBlock(int i, int j) {
        if (i <= 2) {
            if (j <= 2) {
                return 1;
            } else if (j <= 5) {
                return 2;
            } else {
                return 3;
            }
        } else if (i <= 5) {
            if (j <= 2) {
                return 4;
            } else if (j <= 5) {
                return 5;
            } else {
                return 6;
            }
        } else {
            if (j <= 2) {
                return 7;
            } else if (j <= 5) {
                return 8;
            } else {
                return 9;
            }
        }
    }

    // much more concise and readable but slower solution
    public static boolean anotherIsValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                        !seen.add(number + " in column " + j) ||
                        !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }

    // concise, readable, and faster solution with three hashsets
    public static boolean anotherisValidSudoku(char[][] board) {
        for     (int i = 0; i < board.length; i++){
            Set<Character> rows = new HashSet<>();
            Set<Character> columns = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if (board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                if(board[RowIndex + j / 3][ColIndex + j % 3] != '.' && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
                    return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        char[][] board = {
         {'8','3','.','.','7','.','.','.','.'}
        ,{'6','.','.','1','9','5','.','.','.'}
        ,{'.','9','8','.','.','.','.','6','.'}
        ,{'.','.','.','.','6','.','.','.','3'}
        ,{'4','.','.','8','.','3','.','.','1'}
        ,{'7','.','.','.','2','.','.','.','6'}
        ,{'.','6','.','.','.','.','2','8','.'}
        ,{'.','.','.','4','1','9','.','.','5'}
        ,{'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(anotherisValidSudoku(board));
    }
}
