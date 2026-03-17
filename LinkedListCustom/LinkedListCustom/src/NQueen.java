

public class NQueen {
	public static void main(String[] args) {
		int n = 4;
		boolean[][] board = new boolean[4][4];
		int count = queens(board, 0);
		System.out.println(count);
	}
	
	static int queens(boolean[][] data, int row) {
		if(row == data.length) {
			display(data);
			System.out.println();
			return 1;
		}
		int count = 0;
		for(int col = 0 ; col < data[0].length ; col++) {
			if(isSafe(data, row, col)) {
				data[row][col] = true;
				count += queens(data, row+1);
				data[row][col] = false;
			}
		}
		return count;
	}

	private static boolean isSafe(boolean[][] data, int row, int col) {
		// TODO Auto-generated method stub
		//vertical
		for(int i = 0 ; i < row ; i++) {
			if(data[i][col]) {
				return false;
			}
		}
		
		//Diagonal Left 
		int maxLeft = Math.min(row, col); // So the furthest you can go diagonally is min(2, 3) = 2 steps
		for(int i = 1 ; i <= maxLeft ; i++) {
			if(data[row-i][col-i]) {
				return false;
			}
		}
		
		//Diagonal Right
		int maxRight = Math.min(row, data.length - col - 1);
		for(int i = 1 ; i <= maxRight ; i++) {
			if(data[row-i][col+i]) {
				return false;
			}
		}
		
		return true;
	}

	private static void display(boolean[][] data) {
		// TODO Auto-generated method stub
		for(boolean[] row : data) {
			for(boolean i : row) {
				if(i) {
					System.out.print("Q ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}
}
