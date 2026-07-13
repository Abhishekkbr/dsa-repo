import java.util.PriorityQueue;

public class PriorityQueueExample {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(4);
		pq.add(8);
		pq.add(6);
		pq.add(2);
		pq.add(7);
		pq.add(10);
		pq.add(12);
		
		System.out.println(pq);
		System.out.println(pq.poll() + "    " +  pq.poll());
		try {
		int a = 10/0;
		}catch(ArithmeticException e) {
			System.out.println("hello");
		}
		catch(Exception e) {
			System.out.println("Hi");
		}
		
		String s = "abhishek";
		System.out.println(s.charAt(0));
	}
}
