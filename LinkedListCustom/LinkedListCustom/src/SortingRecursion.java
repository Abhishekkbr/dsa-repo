import java.util.Arrays;

public class SortingRecursion {

	public static void main(String[] args) {
//		int[] arr = {3,7,4,8,1,2};
//		selectionSort(arr, arr.length, 0, 0);
//		System.out.println();
//		int[] arrBub = {3,7,4,8,1,2};
//		bubbleSort(arrBub, arrBub.length-1, 0);
		String s = "bcadapplefg";
		String ans = removingChar(s);
		System.out.println(ans);
	}

	private static String removingChar(String un) {
		// TODO Auto-generated method stub
//		if(un.isEmpty()) {
//			System.out.println(p);
//			return;
//		}
//		char ch = un.charAt(0);
//		if(ch == 'a') {
//			removingChar(un.substring(1), p);
//		} else {
//			removingChar(un.substring(1), p+ch);
//		}
		
		if(un.isEmpty()) {
			//System.out.println(p);
			return "";
		}
		char ch = un.charAt(0);
		if(ch == 'a') {
			return removingChar(un.substring(1));
		} else {
			return ch+removingChar(un.substring(1));
		}
	}

	private static void bubbleSort(int[] arr, int i, int j) {
		// TODO Auto-generated method stub
		if(i == 0) {
			return;
		} if(j < i ) {
			if(arr[j] > arr[j+1]) {
				int temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
			}
			bubbleSort(arr, i, j+1);
		} else {
			bubbleSort(arr, i-1, 0);
		}
		
	}

	private static void selectionSort(int[] arr, int row, int col, int max) {
		// TODO Auto-generated method stub
		if(row == 0) {
			return;
		}
		if(col < row) {
			if(arr[col] > arr[max]) {
				selectionSort(arr, row, col+1, col);
			}else{
				selectionSort(arr, row, col+1, max);
			}
			//selectionSort(arr, row, col+1, Math.max(max, col));
			//}
		} else {
			int temp = arr[max];
			arr[max] = arr[row-1];
			arr[row-1] = temp;
			selectionSort(arr, row-1, 0, 0);
		}
		
		//return null;
	}
}
