package com.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

import com.tree.TopView.Node;

public class BottomView {
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
        List<Integer> bottomView = bottomView(root);
        System.out.println(bottomView);
	}

	private static List<Integer> bottomView(Node root) {
		// TODO Auto-generated method stub
		TreeMap<Integer, Integer> result = new TreeMap<>();
		Queue<TreeNodeWithHd> curr = new LinkedList<>();
		curr.add(new TreeNodeWithHd(root, 0));
		while(!curr.isEmpty()) {
			TreeNodeWithHd temp = curr.poll();
			result.put(temp.hd, temp.node.data);
			
			if(temp.node.left != null) {
				curr.add(new TreeNodeWithHd(temp.node.left, temp.hd-1));
			}
			
			if(temp.node.right != null) {
				curr.add(new TreeNodeWithHd(temp.node.right, temp.hd+1));
			}
		}
		return new ArrayList<>(result.values());
	}
}
