package com.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class TreeTraversalIterative {

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
        
        inOrder(root);              //[4, 2, 5, 8, 1, 6, 3, 7]
        preOrder(root);				//[1, 2, 4, 5, 8, 3, 6, 7]
        postOrder(root);			//[4, 8, 5, 2, 6, 7, 3, 1]
        levelOrder(root);			//[1, 2, 3, 4, 5, 6, 7, 8]
        levelOrderByList(root);		//[[1], [2, 3], [4, 5, 6, 7], [8]]
        zigZagTree(root);			//[[1], [3, 2], [4, 5, 6, 7], [8]]
        int min = minDepth(root);
        System.out.println(min);    //3
        int max = maxDepth(root);
        System.out.println(max);    //4
        boolean isBalanced = balanceTree(root);
        System.out.println(isBalanced);
		System.out.println(heightOfTree(root));
	}

	private static boolean balanceTree(Node root) {
		// TODO Auto-generated method stub
		int height = height(root);
		System.out.println("height: "+height);
		return height != -1;
	}

	private static int height(Node root) {
		// TODO Auto-generated method stub
		if(root == null)
			return 0;
		int left = height(root.left);
		int right = height(root.right);
		if(left == -1 || right == -1 || Math.abs(left-right) > 1)
			return -1;
		return 1+Math.max(left, right);
	}

	private static int heightOfTree(Node root) {
		// TODO Auto-generated method stub
		if(root == null)
			return 0;
		int left = height(root.left);
		int right = height(root.right);
		return 1+Math.max(left, right);
	}

	private static int minDepth(Node root) {
		// TODO Auto-generated method stub
		if(root == null) {
			return 0;
		}
		if(root.left == null) {
			return 1+minDepth(root.right);
		}
		if(root.right == null) {
			return 1+minDepth(root.left);
		}
		return 1+Math.min(minDepth(root.left), minDepth(root.right));
	}
	
	private static int maxDepth(Node root) {
		// TODO Auto-generated method stub
		if(root == null) {
			return 0;
		}
		if(root.left == null) {
			return 1+maxDepth(root.right);
		}
		if(root.right == null) {
			return 1+maxDepth(root.left);
		}
		return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
	}

	private static void zigZagTree(Node root) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = new ArrayList<>();
		Queue<Node> curr = new LinkedList<>();
		boolean isOdd = false;
		curr.add(root);
		while(curr.size() > 0) {
			int size = curr.size();
			List<Integer> level = new ArrayList<>();
			while(size-- > 0) {
				Node node = curr.poll();
				level.add(node.data);
				if(node.left != null) {
					curr.add(node.left);
				}
				if(node.right != null) {
					curr.add(node.right);
				}
			}
			if(isOdd) {
				Collections.reverse(level);
			}
			result.add(level);
			isOdd = !isOdd;
		}
		System.out.println(result);
		
	}

	private static void levelOrderByList(Node root) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = new ArrayList<>();
		Queue<Node> curr = new LinkedList<>();
		curr.add(root);
		while(curr.size() > 0) {
			int size = curr.size();
			List<Integer> level = new ArrayList<>();
			while(size-- > 0) {
				Node node = curr.poll();
				level.add(node.data);
				if(node.left != null) {
					curr.add(node.left);
				}
				if(node.right != null) {
					curr.add(node.right);
				}
			} result.add(level);
		}
		System.out.println(result);
		
	}

	private static void levelOrder(Node root) {
		// TODO Auto-generated method stub
		List<Integer> result = new ArrayList<>();
		Queue<Node> curr = new LinkedList<>();
		curr.add(root);
		while(curr.size() > 0) {
			Node node = curr.poll();
			result.add(node.data);
			if(node.left != null) {
				curr.add(node.left);
			}
			if(node.right != null) {
				curr.add(node.right);
			}
		}
		System.out.println(result);
		
	}

	private static void postOrder(Node root) {
		// TODO Auto-generated method stub
		List<Integer> result = new ArrayList<>();
		Stack<Node> curr = new Stack<>();
		curr.add(root);
		while(!curr.isEmpty()) {
			root = curr.pop();
			result.add(root.data);
			if(root.left != null) {
				curr.add(root.left);
			}
			if(root.right != null) {
				curr.add(root.right);
			}
		}
		Collections.reverse(result);
		System.out.println(result);
		
	}

	private static void preOrder(Node root) {
		// TODO Auto-generated method stub
		List<Integer> result = new ArrayList<>();
		Stack<Node> curr = new Stack<>();
		curr.add(root);
		while(!curr.isEmpty()) {
			root = curr.pop();
			result.add(root.data);
			if(root.right != null) {
				curr.add(root.right);
			}
			if(root.left != null) {
				curr.add(root.left);
			}
		}
		System.out.println(result);
		
	}

	private static void inOrder(Node root) {
		// TODO Auto-generated method stub
		List<Integer> result = new ArrayList<>();
		Stack<Node> curr = new Stack<>();
		while(curr.size() > 0 || root != null) {
			while(root != null) {
				curr.add(root);
				root = root.left;
			}
			root = curr.pop();
			result.add(root.data);
			root = root.right;
		}
		System.out.println(result);
	}
}
