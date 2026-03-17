package com.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.tree.TreeTraversalIterative.Node;

public class TreeViews {
	
	static class Node {
		int data;
		Node left;
		Node right;
		
		Node(int data) {
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
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        List<Integer> leftSide = leftSideView(root);
        System.out.println(leftSide);
        
        List<Integer> rightSide = rightSideView(root);
        System.out.println(rightSide);
	}

	private static List<Integer> rightSideView(Node root) {
		// TODO Auto-generated method stub
		List<Integer> result = new ArrayList<>();
		Queue<Node> curr = new LinkedList<>();
		curr.add(root);
		while(!curr.isEmpty()) {
			int size = curr.size();
			result.add(curr.peek().data);
			while(size-- > 0) {
				root = curr.poll();
				if(root.right != null)
					curr.add(root.right);
				if(root.left != null) 
					curr.add(root.left);
			}
		}
		return result;
	}

	private static List<Integer> leftSideView(Node root) {
		// TODO Auto-generated method stub
		List<Integer> result = new ArrayList<>();
		
		Queue<Node> curr = new LinkedList<>();
		curr.add(root);
		while(!curr.isEmpty()) {
			int size = curr.size();
			result.add(curr.peek().data);
			while(size-- > 0) {
				root = curr.poll();
				if(root.left != null)
					curr.add(root.left);
				if(root.right != null) 
					curr.add(root.right);
			}
		}
		return result;
	}
}
