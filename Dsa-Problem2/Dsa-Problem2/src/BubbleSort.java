import java.util.Scanner;


// time complexity = O(n2)  best case = O(n)
public class BubbleSort {

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
		System.out.println("Bubble sorted");
		for(int i = 0; i<arr.length ; i++) {
			for(int j = 1; j<(size-i) ; j++) {
				if(arr[j] < arr[j-1]) {
					temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
		for(int i = 0 ; i<arr.length; i++) {
			System.out.print(arr[i]+ " ");
		}
	}
}
