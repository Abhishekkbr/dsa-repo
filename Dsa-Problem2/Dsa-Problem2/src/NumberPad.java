import java.util.Arrays;

public class NumberPad {

	public static void main(String[] args) {
		pad("", "12");
		System.out.println("===================DICE==================");
		dice("", 4);
		System.out.println("===================MAZE==================");
		int count = mazeCount(3,3);
		System.out.println(count);
		System.out.println("===================MAZE PATH==================");
		mazePath(3,3, "");
		boolean[][] maze = {
				{true, true, true},
				{true, false, true},
				{true, true, true}
		};
		System.out.println("===================MAZE PATH RESTRICTIONS==================");
		pathRestrictions(0, 0, maze, "");
		boolean[][] allMaze = {
				{true, true, true},
				{true, true, true},
				{true, true, true}
		};
		System.out.println("===================MAZE ALL DIRECTIONS==================");
		allDirections(0, 0, allMaze, "");
		int[][] path = new int[allMaze.length][allMaze[0].length];
		System.out.println("===================MAZE ALL DIRECTIONS WITH STEP==================");
		printStep(0, 0, allMaze, "", path, 1);
	}

	private static void printStep(int row, int col, boolean[][] maze, String path, int[][] pathArray, int step) {
		// TODO Auto-generated method stub
		if(row == maze.length-1 && col == maze[0].length-1) {
			pathArray[row][col] = step; 
			for(int[] arr : pathArray) {
				System.out.println(Arrays.toString(arr));
			}
			System.out.println(path);
			System.out.println();
			return;
		}
		if(!maze[row][col]) {
			return;
		}
		maze[row][col] = false;
		pathArray[row][col] = step; 
		if(row < maze.length-1) {
			printStep(row+1, col, maze, path + 'D', pathArray, step+1);
		}
		if(col < maze[0].length-1) {
			printStep(row, col+1, maze, path + 'R', pathArray, step+1);
		}
		if(row > 0) {
			printStep(row-1, col, maze, path + 'U', pathArray, step+1);
		}
		if(col > 0) {
			printStep(row, col-1, maze, path + 'L', pathArray, step+1);
		}
		//for Diagonal
//		if(row < maze.length-1 && col < maze[0].length-1) {
//			mazePath(row+1, col+1, path + 'D');
//		}
		maze[row][col] = true;
		pathArray[row][col] = 0;
	}

	private static void pathRestrictions(int row, int col, boolean[][] maze, String path) {
		// TODO Auto-generated method stub
		if(row == maze.length-1 && col == maze[0].length-1) {
			System.out.println(path);
			return;
		}
		if(!maze[row][col]) {
			return;
		}
		if(row < maze.length-1) {
			pathRestrictions(row+1, col, maze, path + 'V');
		}
		if(col < maze[0].length-1) {
			pathRestrictions(row, col+1, maze, path + 'R');
		}
		//for Diagonal
//		if(row < maze.length-1 && col < maze[0].length-1) {
//			mazePath(row+1, col+1, path + 'D');
//		}
	}
	
	private static void allDirections(int row, int col, boolean[][] maze, String path) {
		// TODO Auto-generated method stub
		if(row == maze.length-1 && col == maze[0].length-1) {
			System.out.println(path);
			return;
		}
		if(!maze[row][col]) {
			return;
		}
		maze[row][col] = false;
		if(row < maze.length-1) {
			allDirections(row+1, col, maze, path + 'D');
		}
		if(col < maze[0].length-1) {
			allDirections(row, col+1, maze, path + 'R');
		}
		if(row > 0) {
			allDirections(row-1, col, maze, path + 'U');
		}
		if(col > 0) {
			allDirections(row, col-1, maze, path + 'L');
		}
		//for Diagonal
//		if(row < maze.length-1 && col < maze[0].length-1) {
//			mazePath(row+1, col+1, path + 'D');
//		}
		maze[row][col] = true;
	}

	private static void mazePath(int row, int col, String path) {
		// TODO Auto-generated method stub
		if(row == 1 && col == 1) {
			System.out.println(path);
			return;
		}
		if(row > 1) {
			mazePath(row-1, col, path + 'V');
		}
		if(col > 1) {
			mazePath(row, col-1, path + 'R');
		}
		//for Diagonal
		if(row > 1 && col > 1) {
			mazePath(row-1, col-1, path + 'D');
		}
		
	}

	private static int mazeCount(int row, int col) {
		// TODO Auto-generated method stub
		if(row == 1 || col == 1) {
			return 1;
		}
		int right = mazeCount(row, col-1);
		int down = mazeCount(row-1, col);
		return right+down;
	}

	private static void dice(String p, int target) {
		// TODO Auto-generated method stub
		if(target == 0) {
			System.out.println(p);
			return;
		}
		for(int i = 1 ; i<=6 && i<=target ; i++) {
			dice(p+i, target - i);
		}
		
	}

	private static void pad(String p, String unp) {
		// TODO Auto-generated method stub
		if(unp.isEmpty()) {
			System.out.println(p);
			return;
		}
		
		int ch = unp.charAt(0) - '0';
		for(int i = (ch-1)*3; i < ch*3 ; i++) {
			char curr = (char) ('a' + i);
			pad(p+curr, unp.substring(1));
		}
	}
}
