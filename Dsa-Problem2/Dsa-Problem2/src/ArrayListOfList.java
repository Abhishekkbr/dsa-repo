import java.util.Scanner;

public class ArrayListOfList {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int visited[] = new int[4];
		System.out.println("Enter the number: ");
		for(int i=0; i<4 ; i++) {
			visited[i] = sc.nextInt();
		}
		System.out.println(minimumSwaps(visited));
	}
	
	static int minimumSwaps(int[] arr) {
        boolean[] track = new boolean[arr.length];
        int swap = 0;
        for(int i = 0 ; i<arr.length ; i++){
            int j = i,cycle = 0;
            while(!track[j]){
                track[j] =  true;
                j = arr[j] - 1;
                cycle++;
            }
            if(cycle!=0){
             swap+=cycle-1;
            }
        }
        return swap;

    }

}
