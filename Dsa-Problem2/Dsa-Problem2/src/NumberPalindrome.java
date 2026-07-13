import java.util.Scanner;

public class NumberPalindrome {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		int sum = 0;
		int tmp = n;
		int r = 0;
		
		while(n>0) {
			r = n%10;
			sum = (sum * 10) + r;
			n = n/10;
		}
		if(tmp == sum) {
			System.out.println("Palindrome");
		}else {
			System.out.println("Not palindrome");
		}
	}
}
