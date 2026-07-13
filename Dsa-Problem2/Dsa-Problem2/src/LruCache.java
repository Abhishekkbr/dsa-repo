import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LruCache {

	private static LinkedHashMap<Integer, Integer> cache;
	private final int CAPACITY;
	public LruCache(int capacity) {
		// TODO Auto-generated constructor stub
		this.CAPACITY = capacity;
		cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return size() > capacity;
			}
		};
	}
	
	public static int get(int key) {
		return cache.getOrDefault(key, -1);
	}
	
	public static void set(int key, int value) {
		//System.out.println("");
		cache.put(key, value);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of the cache");
		int size = sc.nextInt();
		LruCache lc = new LruCache(size);
		while(true) {
			System.out.println("Enter your operation :  1.GET  2.SET  3.SHOW");
			int op = sc.nextInt();
			switch (op) {
				case 1:
					System.out.println("Enter the key you want to get value for :");
					int key = sc.nextInt();
					int find = get(key);
					System.out.println("Key find in :"+find);
					break;
			
				case 2:
					System.out.println("Enter the key to set");
					int k = sc.nextInt();
					System.out.println("Enter the value to set");
					int val = sc.nextInt();
					set(k,val);
					break;
				
				case 3:
					System.out.println("The current hashmap is ----->"+lc.cache);
					break;
			}
		}
		
	}
}
