
public class SlidingWindow {
	public static void main(String[] args) {
		int[] arr = {1, 3, -1, 2};
		int k = 3;
		int maximum = findMax(arr, k);
		System.out.println(maximum);
	}

	private static int findMax(int[] arr, int k) {
		// TODO Auto-generated method stub
		int maxSum = 0;
		int maxWindow = 0;
		
		for(int i = 0 ; i < k ; i++) {
			maxSum += arr[i];
		}
		maxWindow = maxSum;
		for(int i = k ; i < arr.length ; i++) {
			maxWindow += arr[i] - arr[i - k];
			maxSum = Math.max(maxWindow, maxSum);
		}
		
		return maxSum;
	}
}
