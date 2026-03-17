import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class VerticalOrderTraversal {
	static class Node
    {
        int key;
        Node left;
        Node right;
         
        // Constructor
        Node(int data)
        {
            key = data;
            left = null;
            right = null;
        }
    }
	static class Pair{
		Node node;
		int hd;
		Pair(Node node, int hd){
			this.node = node;
			this.hd = hd;
		}
	}
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.left.right = new Node(8);
		root.right.right = new Node(7);
		root.right.right.right = new Node(9);
      System.out.println("Vertical Order traversal is");
      verticalOrder(root);
      vertical(root, 0);
      
      System.out.println("Second way of vertical order");
      for(Map.Entry<Integer, ArrayList<Integer>> mp: map.entrySet()) {
    	  System.out.println(mp.getKey() + "--------------------------->" + mp.getValue());
      }
      
      verticalLast(root);
	}
	
	public static void verticalOrder(Node root){
		LinkedList<Pair> ll = new LinkedList<>();
		HashMap<Integer, List<Integer>> ans = new HashMap<>();
		int min = 0, max = 0;
		ll.addLast(new Pair(root, 0));
		while(ll.size() > 0) {
			int size = ll.size();
			while(size-- > 0) {
				Pair rp = ll.removeLast();
				max = Math.max(max, rp.hd);
				min = Math.min(min, rp.hd);
				ans.putIfAbsent(rp.hd, new ArrayList<>());
				ans.get(rp.hd).add(rp.node.key);
				if(rp.node.left != null)
					ll.addLast(new Pair(rp.node.left, rp.hd-1));
				if(rp.node.right != null)
					ll.addLast(new Pair(rp.node.right, rp.hd+1));
			}
		}
		for(Map.Entry<Integer, List<Integer>> mp: ans.entrySet()) {
			System.out.println(mp.getKey()+"---------------------->"+mp.getValue());
		}
	}
	
//	private static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
//	public static void vertical(Node root, int distance) {
//		if(root == null)
//			return;
//		if(map.containsKey(distance)) {
//			ArrayList<Integer> al = map.get(distance);
//			al.add(root.key);
//			map.put(distance, al);
//			
//		}
//	}
	
	private static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	public static void vertical(Node root, int distance) {
		if(root == null)
			return;
		if(map.containsKey(distance)) {
			ArrayList<Integer> al = map.get(distance);
			al.add(root.key);
			map.put(distance, al);
		}else {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(root.key);
			map.put(distance, temp);
		}
		vertical(root.left, distance-1);
		vertical(root.right, distance+1);
	}
	
	public static void verticalLast(Node root) {
		HashMap<Integer, List<Integer>> mp = new HashMap<>();
		int min_value = Integer.MAX_VALUE;
		int max_value = Integer.MIN_VALUE;
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(root, 0));
		
		while(!queue.isEmpty()) {
			Pair p = queue.remove();
			min_value = Math.min(min_value, p.hd);
			max_value = Math.max(max_value, p.hd);
			if(mp.containsKey(p.hd)) {
				List<Integer> al = mp.get(p.hd);
				al.add(p.node.key);
				mp.put(p.hd, al);
			}else {
				List<Integer> temp = new ArrayList<>();
				temp.add(p.node.key);
				mp.put(p.hd, temp);
			}
			
			if(p.node.left != null) {
				queue.add(new Pair(p.node.left, p.hd-1));
			}
			if(p.node.right != null) {
				queue.add(new Pair(p.node.right, p.hd+1));
			}
		}
		System.out.println("Last new vertical order traversal");
		while(min_value <= max_value) {
		//for(Map.Entry<Integer, List<Integer>> ans: mp.entrySet()) {
			System.out.println(mp.get(min_value).get(0));
			min_value++;
		//}
		}
	}
}
