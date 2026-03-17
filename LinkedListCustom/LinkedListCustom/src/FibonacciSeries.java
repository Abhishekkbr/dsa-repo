import java.util.Scanner;

public class FibonacciSeries {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the range: ");
		int num = sc.nextInt();
		System.out.println("Fibonacci without recursion :");
		fibonacci(num);
		
		System.out.println("Fibonacci using recursion :");
		System.out.println(recFibonacci(num));
	}

	private static int recFibonacci(int num) {
		// TODO Auto-generated method stub
		if(num <= 1) {
			return num;
		}
		
		return recFibonacci(num - 1) + recFibonacci(num - 2);
		
	}

	private static void fibonacci(int num) {
		// TODO Auto-generated method stub
		int a = 0;
		int b = 1;
		int c = 1;
		
		for (int i = 0 ; i <= num ; i++) {
			System.out.print(a + " ");
			a = b;
			b = c;
			c = a + b;
		}
		
	}
}
