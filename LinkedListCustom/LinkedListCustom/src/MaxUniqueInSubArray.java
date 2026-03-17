import java.util.*;

public class MaxUniqueInSubArray {
	
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 Deque<Integer> deque = new ArrayDeque<>();
		 HashSet<Integer> set = new HashSet<>();
		 System.out.println("Enter the amount of element :");
		 int n = in.nextInt();
		 System.out.println("Enter the subarray size :");
		 int m = in.nextInt();
		 System.out.println("Enter the elements :");
		 int[] arr = new int[n];
		 for (int i = 0; i < n; i++) {
			 arr[i] = in.nextInt();
		 }
		 System.out.println("Max size : "+ getMaxSize(m, arr));
	 }

	private static int getMaxSize(int m, int[] arr) {
		Map<Integer, Integer> freq = new HashMap<>();
		int max = 0;

		for(int i = 0 ; i < arr.length ; i++) {
			freq.put(arr[i], freq.getOrDefault(arr[i], 0)+1);

			if (i >= m) {
				int curr = arr[i-m];
				freq.put(curr, freq.get(curr)-1);
				if(freq.get(curr) == 0) {
					freq.remove(curr);
				}
			}

			if (i >= m-1) {
				max = Math.max(max, freq.size());
			}
		}
		return max;
 	}

}
