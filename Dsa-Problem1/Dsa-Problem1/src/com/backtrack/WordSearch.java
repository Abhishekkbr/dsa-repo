package com.backtrack;

// O(N * 3^L)  N = Number of cells (row * col) L = length of word
public class WordSearch {
    public static void main(String[] args) {
        WordSearch solver = new WordSearch();

        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String word = "ABCCED";

        System.out.println(solver.exist(board, word)); // Output: true
    }

    private boolean exist(char[][] board, String word) {
        int rows = board.length;
        int col = board[0].length;
        for (int i = 0 ; i < rows ; i++) {
            for (int j = 0 ; j < col ; j++) {
                if(findWord(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findWord(char[][] board, String word, int row, int col, int index) {
        if(index == word.length()) {
            return true;
        }
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length || word.charAt(index) != board[row][col]) {
            return false;
        }
        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = findWord(board, word, row, col+1, index+1) ||
                findWord(board, word, row, col-1, index+1) ||
                findWord(board, word, row+1, col, index+1) ||
                findWord(board, word, row-1, col, index+1);

        board[row][col] = temp;

        return found;
    }
}
