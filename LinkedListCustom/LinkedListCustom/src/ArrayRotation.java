import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ArrayRotation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT );
		System.out.println("time: "+s);
		System.out.println("Enter the size :");
		int size = sc.nextInt();
		System.out.println("Enter the number of rotation:");
		int rotate = sc.nextInt();
		int[] a = new int[size];
		System.out.println("Enter the elements");
		for(int i = 0; i<size; i++) {
			a[i] = sc.nextInt();
		}
		int[] result = rotatedArray(a,rotate);
		for(int i=0; i<result.length ; i++) {
			System.out.print(result[i] + " ");
		}
		
	}

	private static int[] rotatedArray(int[] a, int rotate) {
		// TODO Auto-generated method stub
		int[] result = new int[a.length];
		for(int i = 0 ; i < a.length ; i++) {
			int newIndex = (i+rotate)%a.length;
			result[i] = a[newIndex];
		}
		return result;
	}
	
//	private static int[] rotatedArray(int[] arr, int rotate) {
//		int[] newArr = new int[arr.length];
//		for (int i = 0 ; i < arr.length ; i++) {
//			int idx = (i + rotate) % arr.length;
//			newArr[i] = arr[idx];
//		}
//		return newArr;
//	}
}	
