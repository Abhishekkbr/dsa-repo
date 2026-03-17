import java.util.Scanner;

public class PallindromeExp {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string: ");
		String s = sc.next();
		System.out.println(palindromCheck(s));
		System.out.println(palindromeSecond(s));
	}

	private static boolean palindromeSecond(String s) {
		// TODO Auto-generated method stub
		String reverse = "";
		for(int i = s.length() - 1 ; i >= 0 ; i--) {
			reverse = reverse + s.charAt(i);
		}
		if(reverse.equals(s)) {
			return true;
		}
		return false;
	}

	private static boolean palindromCheck(String s) {
		s = s.toLowerCase();
		boolean result = true;
		// TODO Auto-generated method stub
		for(int i = 0 ; i <= s.length()/2 ; i++) {
			if(s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
				
			}
		}
		return true;
	}
	
	

}
