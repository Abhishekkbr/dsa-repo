
public class ThreadTest {
	public static void main(String[] args) {
		int[] a = new int[10];
		for(int i = 0 ; i<10 ; i++) {
			a[i] = i;
		}
		
		int[] sam = new int[100];
		//for(int i = 6 ; i < 10 ; i++) {
			sam[7]++;
			//sam[a[i]]--;
		//}
		
		a[7]--;
		for(int i = 0 ; i < 10 ; i++) {
			
			System.out.println(a[i]);
		}
		
		//System.out.println(3/2);
	}
}
