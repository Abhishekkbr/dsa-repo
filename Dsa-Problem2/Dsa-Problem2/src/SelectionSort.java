import java.util.Scanner;

//time complexity = O(n2)
public class SelectionSort {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		int size = sc.nextInt();
		int arr[] = new int[size];
		System.out.println("Enter the elements of the array: ");
		for(int i=0 ; i<size ; i++) {
			arr[i] =  sc.nextInt();
		}
		int temp = 0;
		System.out.println("Quick sorted");
		
		
//		for(int i = 0 ; i<arr.length ; i++) {
//			int min = i;
//			for(int j = i + 1 ; j < arr.length ; j++) {
//				if(arr[j] < arr[min]) {
//					min = j;
//				}
//			}
//			temp = arr[min];
//			arr[min] = arr[i];
//			arr[i] = temp;
//		}
		
//		for(int i = 0 ; i < arr.length-1 ; i++) {
//			int min = i;
//			for(int j = i+1 ; j < arr.length ; j++) {
//				if(arr[j] < arr[min])
//					min = j;
//			}
//			temp = arr[min];
//			arr[min] = arr[i];
//			arr[i] = temp;
//		}
		
		for(int i = 0 ; i < arr.length ; i++) {
			int min = i;
			for(int j = i+1; j < arr.length ; j++) {
				if(arr[j] < arr[min])
					min = j;
			}
				temp = arr[min];
				arr[min] = arr[i];
				arr[i] = temp; 
		}
		
		for(int i = 0; i < arr.length ; i++) {
			System.out.print(arr[i] +" ");
		}
	}
}
