import java.util.Scanner;
import java.util.concurrent.ExecutorService;

//time complexity = O(n2)  best case = O(nlogn)
public class QuickSort {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		int size = sc.nextInt();
		int arr[] = new int[size];
		System.out.println("Enter the elements of the array: ");
		for(int i=0 ; i<size ; i++) {
			arr[i] =  sc.nextInt();
		}
		System.out.println("quick sorted array :");

		sort(arr, 0, size-1);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	private static void sort(int[] arr, int low, int high) {
		// TODO Auto-generated method stub
//		if(low < high) {
//			int pi = partition(arr, low, high);
//			sort(arr, 0, pi-1);
//			sort(arr, pi+1, high);
//		}
		
		if(low < high) {
			int pi = part(arr, low, high);
			sort(arr, low, pi-1);
			sort(arr, pi+1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		// TODO Auto-generated method stub
		int pivot = arr[high];
		int i = low-1;
		for(int j = low; j<high ;j++) {
			if(arr[j] < pivot) {
				i++;
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		
		return i+1;
	}
	
	public static int part(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low - 1;
		for(int j = low ; j < high ; j++) {
			if(arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		
		return i+1; 
	}
}
