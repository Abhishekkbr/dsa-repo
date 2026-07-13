import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BuildingAreaProblem {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Integer> area = new ArrayList<>();
		System.out.println("Enter the number of buildings :");
		int n = sc.nextInt();
		System.out.println("Enter the elements :");
		for(int i=0 ; i<n ; i++) {
			int a = sc.nextInt();
			area.add(a);
		}
		
		long result = largestRectangle(area);
		System.out.println("result : "+result);  // 11 11 10 10 10 //1 3 5 9 11
		
	}
	
	public static long largestRectangle(List<Integer> h) {
	    // Write your code here
	    Stack<Integer> st1 = new Stack<>();
	    int i = 0;
	    int topIndex = 0;
	    long area;
	    long finalArea=0;
	    while(i<h.size()){
	      if(st1.isEmpty() || h.get(st1.peek()) <= h.get(i)){
	        st1.push(i++);  //0-1-2-3-4
	      }else{
	        topIndex = st1.peek();
	        st1.pop();
	        
	        area = h.get(topIndex) * (st1.isEmpty() ? i : i-1-st1.peek()); //11*(2-1-0)->
	        //---->11*2
	        if(area > finalArea ){
	          finalArea = area;
	        }
	      }
	    }
	    while(st1.isEmpty()==false){
	      topIndex = st1.peek();
	      st1.pop();
	      area = h.get(topIndex) * (st1.isEmpty() ? i : i-1-st1.peek());  //5-1-3 = 11*1   18 5*3
	      	//3 * 5-1-0   1*5   -------> 10*(4-1-3)  10*(5-1-2) 
	        if(area > finalArea ){
	          finalArea = area;
	        }
	    }
	    return finalArea;

	    }

}
