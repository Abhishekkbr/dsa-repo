package com.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class TopView {
	static class Node {
		int data;
		Node left;
		Node right;
		
		Node(int data) {
			this.data = data;
			left = right = null;
		}
	}
	
	static class TreeNodeWithHd {
		Node node;
		int hd;
		public TreeNodeWithHd(Node node, int hd) {
			this.node = node;
			this.hd = hd;
		}
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(8);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        List<Integer> topView = topView(root);
        System.out.println(topView);
        List<Integer> topViewHashMap = topViewHashMap(root);
        System.out.println(topViewHashMap);
	}

	private static List<Integer> topViewHashMap(Node root) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		Queue<TreeNodeWithHd> curr = new LinkedList<>();
		List<Integer> result = new ArrayList<>();
		int min = 0; 
		int max = 0;
		curr.add(new TreeNodeWithHd(root, 0));
		while (!curr.isEmpty()) {
			TreeNodeWithHd temp = curr.poll();
			if(!hm.containsKey(temp.hd)) {
				hm.put(temp.hd, temp.node.data);
				max = Math.max(max, temp.hd);
				min = Math.min(min, temp.hd);
			}
			if(temp.node.left != null) {
				curr.add(new TreeNodeWithHd(temp.node.left, temp.hd-1));
			}
			if(temp.node.right != null) {
				curr.add(new TreeNodeWithHd(temp.node.right, temp.hd+1));
			}	
		}
		// TODO Auto-generated method stub
		for(int i = min; i <= max ; i++) {
			result.add(hm.get(i));
		}
		return result;
	}

	private static List<Integer> topView(Node root) {
		// TODO Auto-generated method stub
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		Queue<TreeNodeWithHd> curr = new LinkedList<>();
		curr.add(new TreeNodeWithHd(root, 0));
		while(!curr.isEmpty()) {
			TreeNodeWithHd temp = curr.poll();
			if(!tm.containsKey(temp.hd)) {
				tm.put(temp.hd, temp.node.data);
			}
			if(temp.node.left != null) {
				curr.add(new TreeNodeWithHd(temp.node.left, temp.hd-1));
			}
			if(temp.node.right != null) {
				curr.add(new TreeNodeWithHd(temp.node.right, temp.hd+1));
			}
		}
		return new ArrayList<>(tm.values());
	}

}
