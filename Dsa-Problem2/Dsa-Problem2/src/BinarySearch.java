import java.util.Scanner;

public class BinarySearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of elements :");
		int size = sc.nextInt();
		int a[] = new int[size];
		System.out.println("Enter the elements :");
		for(int i = 0; i < size ; i++) {
			a[i] = sc.nextInt();
		}
		System.out.println("Enter the element to search :");
		int find = sc.nextInt();
		int result = binarySearch(a, 0, size - 1, find);
		System.out.println("The element is in :"+(result));
	}

	private static int binarySearch(int[] a, int low, int high, int find) {
		// TODO Auto-generated method stub
		int mid = (low + high)/2;
		while(low <= high) {
			if(a[mid] == find) {
				return mid;
			}else if(a[mid] < find) {
				low = mid + 1;
			}else {
				high = mid - 1;
			}
			mid = (low + high) / 2;
		}
		if (low > high) {
			return -1;
		}
		return -1;
	}
}
