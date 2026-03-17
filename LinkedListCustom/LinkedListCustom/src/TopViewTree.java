import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class TopViewTree {
	
	static class Node {
		int data;
		Node left;
		Node right;
		
		Node(int data){
			this.data = data;
			left = right = null;
		}
	}
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(8);
        root.left.right.right.right = new Node(9);
        root.left.right.right.right = new Node(10);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println("Top view of the given tree:");
        topView(root);
        System.out.println("Done.........");
	}
	
	private static void topView(Node root) {
		// TODO Auto-generated method stub
		if(root == null)
			return;
		
		Map<Integer, Integer> mp = new HashMap<>();
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		mp.put(0, root.data);
		while(!q.isEmpty()) {
			Node curr = q.poll();
			int hd = curr.data;
			if(curr.left != null) {
				hd = hd-1;
				q.add(curr.left);
				if(!mp.containsKey(hd)) {
					mp.put(hd, curr.left.data);
				}
			}
			if(curr.right != null) {
				hd = hd+1;
				q.add(curr.right);
				if(mp.containsKey(hd)) {
					mp.put(hd,  curr.right.data);
				}
			}
		}
		List<Integer> ar = new ArrayList<>(mp.values());
		Collections.sort(ar);
		System.out.println("root print: {}");
		for(int i : ar) {
			System.out.println(i + " ");
		}
	}
}
