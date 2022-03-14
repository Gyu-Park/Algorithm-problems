package Problems;

public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (isThereTheWord(board, word, i, j, 0))
                        return true;
                }
            }
        }

        return false;
    }

    private static boolean isThereTheWord(char[][] board, String word, int m, int n, int wordIndex) {
        if (wordIndex == word.length())
            return true;
        if (m < 0 || m > board.length - 1 || n < 0 || n > board[0].length - 1
                || board[m][n] != word.charAt(wordIndex)) {
            return false;
        }
        board[m][n] = '0';
        boolean res = isThereTheWord(board, word, m - 1, n, wordIndex + 1)
                || isThereTheWord(board, word, m + 1, n, wordIndex + 1)
                || isThereTheWord(board, word, m, n - 1, wordIndex + 1)
                || isThereTheWord(board, word, m, n + 1, wordIndex + 1);
        board[m][n] = word.charAt(wordIndex);

        return res;
    }

    static boolean[][] visited;

    public static boolean anotherExist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((word.charAt(0) == board[i][j]) && isThereTheWord2(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isThereTheWord2(char[][] board, String word, int m, int n, int wordIndex) {
        if (wordIndex == word.length()) {
            return true;
        }
        if (m < 0 || m > board.length - 1 || n < 0 || n > board[0].length - 1 || board[m][n] != word.charAt(wordIndex)
                || visited[m][n]) {
            return false;
        }
        visited[m][n] = true;
        if (isThereTheWord2(board, word, m - 1, n, wordIndex + 1) ||
                isThereTheWord2(board, word, m + 1, n, wordIndex + 1) ||
                isThereTheWord2(board, word, m, n - 1, wordIndex + 1) ||
                isThereTheWord2(board, word, m, n + 1, wordIndex + 1)) {
            return true;
        }
        visited[m][n] = false;

        return false;
    }

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        String word = "ABCCED";
        System.out.println(exist(board, word));
        System.out.println(anotherExist(board, word));
    }
}
