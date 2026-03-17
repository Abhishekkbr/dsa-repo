
public class NKnights {
	public static void main(String[] args) {
		int n = 4;
		boolean[][] board = new boolean[n][n];
		int count = nKnights(board, 0);
		System.out.println(count);
	}

	private static int nKnights(boolean[][] knights, int row) {
		if (knights.length == row) {
			display(knights);
			System.out.println();
			return 1;
		}

		int count = 0;
		for(int col = 0  ; col < knights.length ; col++) {
			if(isSafe(knights, row, col)) {
				knights[row][col] = true;
				count += nKnights(knights, row+1);
				knights[row][col] = false;
			}
		}
		return count;
	}

	public static boolean isSafe(boolean[][] knights, int row, int col) {
		if(isValid(knights, row-1, col-2)) {
			if(knights[row-1][col-2]) {
				return false;
			}
		}
		if(isValid(knights, row-1, col+2)) {
			if(knights[row-1][col+2]) {
				return false;
			}
		}
		if(isValid(knights, row-2, col-1)) {
			if(knights[row-2][col-1]) {
				return false;
			}
		}
		if(isValid(knights, row-2, col+1)) {
			if(knights[row-2][col+1]) {
				return false;
			}
		}
		if(isValid(knights, row, col)) {
			if(knights[row][col]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValid(boolean[][] knights, int row, int col) {
		return row >= 0 && row < knights.length && col >= 0 && col < knights.length;
	}


//	public static int nKnights(boolean[][] knights, int row) {
//		if(knights.length == row) {
//			display(knights);
//			System.out.println();
//			return 1;
//		}
//
//		int count = 0;
//		for(int col = 0 ; col < knights.length ; col++) {
//			if(isSafe(knights, row, col)) {
//				knights[row][col] = true;
//				count += nKnights(knights, row+1);
//				knights[row][col] = false;
//			}
//		}
//		return count;
//	}
//
//	private static boolean isSafe(boolean[][] knights, int row, int col) {
//		// TODO Auto-generated method stub
//		if(isValid(knights, row-1, col+2)) {
//			if(knights[row - 1][col+2]) {
//				return false;
//			}
//		}
//		if(isValid(knights, row-1, col-2)) {
//			if(knights[row - 1][col-2]) {
//				return false;
//			}
//		}
//		if(isValid(knights, row-2, col-1)) {
//			if(knights[row - 2][col-1]) {
//				return false;
//			}
//		}
//		if(isValid(knights, row-2, col+1)) {
//			if(knights[row - 2][col+1]) {
//				return false;
//			}
//		}
//
//		if(isValid(knights, row, col)) {
//			if(knights[row][col]) {
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	private static boolean isValid(boolean[][] knights, int row, int col) {
//		// TODO Auto-generated method stub
//		if(row >= 0 && row < knights.length && col >= 0 && col < knights.length) {
//			return true;
//		}
//		return false;
//	}

	private static void display(boolean[][] knights) {
		// TODO Auto-generated method stub
		for(boolean[] knight : knights) {
			for(boolean curr : knight) {
				if(curr) {
					System.out.print("K ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}
}
