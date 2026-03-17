
public class PalindromeSubString {
	public static void main(String[] args) {
		String str = "babad";
	    int count = findAllPalindromeSubstrings(str);
	    System.out.println("Total palindrome substrings: " + count);
	}

	private static int findAllPalindromeSubstrings(String str) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int i=0 ; i<str.length() ; i++) {
			for(int j = i+1 ; j<str.length() ; j++) {
				if(isPalindrome(str,i,j)) {
					System.out.println(str.substring(i,j+1));
					count++;
				}
			}
		}
		return count;
	}

	private static boolean isPalindrome(String input, int i, int j) {
		// TODO Auto-generated method stub
//		while(j>i) {
//			if(input.charAt(i) != input.charAt(j)) {
//				return false;
//			}
//			i++;
//			j--;
//		}
//		return true;
		while(i < j) {
			if(input.charAt(i) != input.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
