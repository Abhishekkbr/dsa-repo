import java.util.Arrays;
import java.util.Scanner;

public class PythogoreanTriplet {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] input = { 3, 1, 4, 6, 5, 12, 13};
		int n = input.length;
		Arrays.sort(input);
		int[] arr = Arrays.stream(input).map(i -> i*i).toArray();
		//System.out.println(Arrays.toString(arr));
		for(int i = n-1 ; i > 1 ; i--) {
			int l = 0;
			int r = i-1;
			while(l < r) {
				if(arr[l] + arr[r] == arr[i])
					System.out.println(arr[l]+"====>"+arr[r]+"   "+arr[i]);
				
				if(arr[l] + arr[r] > arr[i])
					r--;
				else
					l++;
			}
		}
	}
}
