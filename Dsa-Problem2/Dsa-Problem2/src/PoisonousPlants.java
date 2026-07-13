//import java.awt.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PoisonousPlants {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of elements :");
		int n = sc.nextInt();
		List<Integer> a = new ArrayList<>();
		System.out.println("Enter the elements :");
		for(int i=0 ; i<n ; i++) {
			int value = sc.nextInt();
			a.add(value);
		}
		int result = poisonousPlants(a);
		System.out.println("result : "+result);
//		Stack<List<Integer>> stack = new Stack<>();
//        List<Integer> store = new ArrayList<>();
//        
//        store.add(0);
//        store.add(1);
//        stack.push(store);
//        System.out.println(stack.peek().get(0));
		
	}
	
	public static int poisonousPlants(List<Integer> p) {
	    // Write your code here
	            Stack<List<Integer>> stack = new Stack<>();
	            List<Integer> store = new ArrayList<>();
	            // int[] demo = new demo[p.size()];
	            // for(int i=0 ; i<demo.length ; i++){
	            //     demo[i] = p.get(i);
	            // }
	            int maxDay = 0;
	            for(Integer i:p){
	                int day = 0;
	                System.out.println(stack.peek());
	                while(!stack.isEmpty() && stack.peek().get(0) >= p.get(i)){
	                    System.out.println("pop: "+stack.pop());
	                    day = Math.max(day, stack.pop().get(1));
	                } 
	                if(!stack.isEmpty()){
	                    day++;
	                }else{
	                    day = 0;
	                }
	                
	                maxDay = Math.max(maxDay, day);
	                store.add(i);
	                store.add(day);
	                stack.push(store);
	            }
	        return maxDay;
	    }
}
