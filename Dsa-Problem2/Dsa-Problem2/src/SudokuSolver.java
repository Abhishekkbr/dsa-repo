
public class SudokuSolver {
	public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','9','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(isValidSudoku(board));
    }

    private static boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0 ; r < board.length ; r++) {
            for (int c = 0 ; c < board.length ; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    continue;
                }
                int index = ch - '1';   // so can calculate from 0, 8
                int box = (r/3) * 3 + (c/3);
                if (rows[r][index] || cols[c][index] || boxes[box][index]) {
                    return false;
                }
                rows[r][index] = true;
                cols[c][index] = true;
                boxes[box][index] = true;
            }
        }
        return true;
    }


}
