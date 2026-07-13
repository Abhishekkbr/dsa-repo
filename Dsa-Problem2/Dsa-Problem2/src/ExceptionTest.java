import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ExceptionTest implements Serializable {
	
	static class Employee{
		int id;
		public Employee(int id) {
			// TODO Auto-generated constructor stub
			this.id = id;
		}
	}
	//private Ini
	public static void main(String[] args) {
		//int a = test();
//		double a =  (double) 0.5;
//		double d1 = 84.2;
//		System.out.println(a);
		//System.out.println(Math.ceil(d1));
		
		int[] arr = {3,1,2,3,1,2,3};
		int[] sorted = minSort(arr);
		Arrays.stream(sorted).forEach(s -> System.out.print(s+ " "));
		
		int a = test();
		System.out.println(a);
		char[] ch = new char[10];
		System.out.println("testch: "+ch[2]);
		
		int x = 10;
		int y = -x;
		System.out.println(y);
		
		char c = 'a';
		String s = "";
		
		System.out.println(s+c);
		
		System.out.println(Operator.getOperatorFromVehicleType(6));
		
		String ab = "-2";
		System.out.println(ab.length());
		
		
		
		Employee e = new Employee(1);
		Employee e1 = new Employee(1);
		
		Set<Employee> semp = new HashSet<>();
		semp.add(e);
		semp.add(e1);
		System.out.println("semp ---------->"+semp);
		
		
		String pool = "abhishek";
		String newPool = new String("abhishek");
		
		Set<String> sp = new HashSet<>();
		sp.add(pool);
		sp.add(newPool);
		System.out.println("sp ---------->"+sp);
		
 	}
	
	private static int[] minSort(int[] nums) {
		int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        
        while(mid <= high){
            if(nums[mid] == 2)
                mid++;
            
            else if(nums[mid] == 1){
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                low++;
                mid++;
            }
            
            else{
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }return nums;
	}
	
	private static int test() {
		// TODO Auto-generated method stub
		try {
			int x = 10/0;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("In catch block");
			//System.exit(1);
			return 2;
		}
		finally {
			System.out.println("In finally block");
			return 3;
		}
	}
	
	public enum Operator {
	    MARTA(0,1,2,3,4),
	    CCT(5,6,7,8),
	    GCT(9,10),
	    GRTA(11);

	    private List<Integer> text;

	    Operator(Integer ...text){
	        this.text = Arrays.asList(text);
	    }

	    public List<Integer> getValues() {
	        return text;
	    }

	    public static String getOperatorFromVehicleType(int vehicleType) {
	        for(Operator operator : Operator.values() ) {
	            if(operator.getValues().contains(vehicleType)) {
	                return operator.toString();
	            }
	        }
	        return null;
	    }
	}
}
