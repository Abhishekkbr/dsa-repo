import java.util.Scanner;

public class ContiansVowels {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value :");
		String s = sc.next();
		System.out.println(isContainsVowels(s));
		//System.out.println(s.substring(0,2));
	}

	private static boolean isContainsVowels(String s) {
		// TODO Auto-generated method stub
		return s.toLowerCase().matches(".*[aeiou].*");
	}

}
