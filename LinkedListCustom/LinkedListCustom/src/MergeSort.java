import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort {
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

		sort(arr, 0 , size-1);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	private static void sort(int[] arr, int l, int r) {
		// TODO Auto-generated method stub
//		if(l < r) {
//			int m = l + (r-l)/2;
//			
//			sort(arr, l, m);
//			sort(arr, m+1, r);
//			
//			merge(arr, l, m, r);
//		}
		if (l < r) {
			int m = l + (r-l)/2;
			sort(arr, l, m);
			sort(arr, m+1, r);
			merge(arr, l, m, r);
		}
	}

	private static void merge(int[] arr, int l, int m, int r) {
		// TODO Auto-generated method stub
//		int lsize = m-l+1;
//		int rsize = r-m;
//		
//		int[] larr = new int[lsize];
//		int[] rarr = new int[rsize];
//		
//		
//		for(int i = 0 ; i < lsize ; i++) {
//			larr[i] = arr[i+l];
//		}
//		for(int i = 0 ; i < rsize ; i++) {
//			rarr[i]  = arr[m+1+i];
//		}
//		
//		int i = 0, j = 0;
//		int k = l;
//		
//		while(i < lsize && j < rsize) {
//			if(larr[i] > rarr[j]) {
//				arr[k++] = rarr[j++];
//			}else {
//				arr[k++] = larr[i++];
//			}
//		}
//		
//		while(i < lsize) {
//			arr[k++] = larr[i++];
//		}
//		
//		while(j < rsize) {
//			arr[k++] = rarr[j++];
//		}
		int lsize = m-l+1;
		int rsize = r-m;
		int[] larr = new int[lsize];
		int[] rarr = new int[rsize];
		for(int i = 0 ; i < lsize ; i++) {
			larr[i] = arr[i];
		}
		for(int i = 0 ; i < rsize ; i++) {
			rarr[i] = arr[m+i+1];
		}
		int i = 0, j = 0;
		int k = l;
		while(i < lsize && j < rsize) {
			if(larr[i] >= rarr[j]) {
				arr[k++] = rarr[j++]; 
			}else {
				arr[k++] = larr[i++];
			}
		}
		while(i < lsize)
			arr[k++] = larr[i++];
		while(j < rsize)
			arr[k++] = rarr[j++];
	}	
}
