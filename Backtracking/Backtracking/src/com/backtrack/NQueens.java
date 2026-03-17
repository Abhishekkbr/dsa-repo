package com.backtrack;

public class NQueens {
	public static void main(String[] args) {
		int size = 4;
		boolean [][] board = new boolean[size][size];
		int count = nQueens(board, 0);
		System.out.println(count);
	}

	private static int nQueens(boolean[][] board, int row) {
		// TODO Auto-generated method stub
		if(board.length == row) {
			return 1;
		}
		int count = 0;
		for(int col = 0 ; col < board.length ; col++) {
			if(isSafe(board, row, col)) {
				board[row][col] = true;
				count+=nQueens(board, row+1);
				board[row][col] = false;
			}
		}
		return count;
	}

	private static boolean isSafe(boolean[][] board, int row, int col) {		
		//for checking in column
		for(int i = 0 ; i < row ; i++) {
			if(board[i][col]) {
				return false;
			}
		}
		
		//for left diagonal
		for(int i = 0 ; i < row ; i++) {
			int leftDiagonal = col - (row - i);
			if(leftDiagonal >= 0 && board[i][leftDiagonal]) {
				return false;
			}
		}
		
		//for right diagonal
		for(int i = 0 ; i < row  ; i++) {
			int rightDiagonal = col + (row - i);
			if(rightDiagonal < board.length && board[i][rightDiagonal]) {
				return false;
			}
		}
		return true;
	}
}
