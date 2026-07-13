import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComparableTest {
	static class Employee{
		int id;
		String name;
		
		public Employee(int id, String name) {
			// TODO Auto-generated constructor stub
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + "]";
		}
		
		
	}
	static int count = 0;
	public static void main(String[] args) {
		Employee e1 = new Employee(2, "singh");
		Employee e2 = new Employee(1,"ziara");
		Employee e3 = new Employee(3,"abhi");
		List<Employee> ll = new ArrayList<>();
		ll.add(e1);
		ll.add(e2);
		ll.add(e3);
//		Collections.sort(ll, new Comparator<Employee>() {
//
//			@Override
//			public int compare(Employee arg0, Employee arg1) {
//				// TODO Auto-generated method stub
//				return arg0.getName().compareTo(arg1.getName());
//			}
//			
//		});
		
		List<Employee> lll = ll.stream().sorted(Comparator.comparing(Employee :: getId).reversed()).collect(Collectors.toList());
		Optional<String> max = ll.stream().map(Employee::getName).max(Comparator.naturalOrder());
		System.out.println(max);
		System.out.println(lll);
//		System.out.println();
//		if(count < 3) {
//			count++;
//			main(null);
//		}else {
//			return;
//		}
//		System.out.println("hello");
//		
//		
	}

}
