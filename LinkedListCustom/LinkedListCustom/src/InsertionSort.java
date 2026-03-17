import java.util.Scanner;

// time complexity = O(n2)  best case = O(n)
public class InsertionSort {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		int size = sc.nextInt();
		int arr[] = new int[size];
		System.out.println("Enter the elements of the array: ");
		for(int i=0 ; i<size ; i++) {
			arr[i] =  sc.nextInt();
		}
		System.out.println("Insertion sorted array :");

		sort(arr);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	private static void sort(int[] arr) {
		// TODO Auto-generated method stub
//		for(int i=1 ; i<arr.length ; i++) {
//			int key = arr[i];
//			int j = i-1;
//			while(j >= 0 && arr[j] > key) {
//				arr[j+1] = arr[j];
//				j--;
//			}
//			arr[j+1] = key;
//		}
		for(int i = 1 ; i < arr.length ; i++) {
			int key = arr[i];
			int j = i-1;
			while(j >= 0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = key;
		}
		
	}
}
