import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyDenominaiton {

	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the amount : ");
		int curr = sc.nextInt();
		lowestCurrency(curr);
	}

	private static void lowestCurrency(int curr) {
		// TODO Auto-generated method stub
		//int[] arr = new int[] {10,20,50,100,200,500,1000};
		int[] arr = {1000,500,200,100,50,20,10};
		Map<Integer, Integer> mp = new HashMap<>();
		int i = 0;
//		int first = 0;
//		if(curr >= arr[0]) {
//			mp.put(arr[0], curr/arr[0]);
//			curr = curr - (mp.get(arr[0]) * arr[0]);
//		}
		while(curr != 0 && i < arr.length) {
			if(arr[i] <= curr) {
				mp.put(arr[i], curr/arr[i]);
				curr = curr - (mp.get(arr[i]) * arr[i]);
			}
//			else {
//				mp.put(arr[i], curr/arr[i]);
//				curr = curr - (mp.get(arr[i]) * arr[i]);
//			}
			i++;
		}
		System.out.println(mp);
	}
	
}
