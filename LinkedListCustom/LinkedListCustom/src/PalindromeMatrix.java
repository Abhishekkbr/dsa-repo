import java.util.Scanner;

public class PalindromeMatrix {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String temp = "abccbc";
		int count = countPalindrome(temp);
		System.out.println(count);
	}

	private static int countPalindrome(String temp) {
		// TODO Auto-generated method stub
		boolean[][] bool = new boolean[temp.length()][temp.length()];
		int count = 0;
		for(int g = 0 ; g < temp.length() ; g++) {
			for(int i = 0, j = g ; j < bool.length ; i++ ,j++) {
				if(g == 0) {
					bool[i][j] = true;
				}else if( g == 1) {
					if(temp.charAt(i) == temp.charAt(j)) {
						bool[i][j] = true;
					}else {
						bool[i][j] = false;
					}
				}else {
					if(temp.charAt(i) == temp.charAt(j) && bool[i+1][j-1] == true) {
						bool[i][j] = true;
					}else {
						bool[i][j] = false;
					}
				}
				if(bool[i][j] == true)
					count++;
			}
		}
		return count;
	}
}
