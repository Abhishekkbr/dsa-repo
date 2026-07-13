import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;


//class Node{
//	int data;
//	Node left, right;
//	Node(int data){
//		this.data = data;
//		this.left = null;
//		this.right = null;
//	}
//}
//public class LevelOrderTree {
//	
//	static Node root;
//	
//	void printNode() {
//		Queue<Node> queue = new LinkedList<Node>();
//		queue.add(root);
//		
//		while(!queue.isEmpty()) {
//			Node temp = queue.poll();
//			System.out.print(temp.data + "  ");
//			if(temp.left != null)
//				queue.add(temp.left);
//			if(temp.right != null)
//				queue.add(temp.right);
//		}
//		System.out.println();
//	}
//	
//	static void leftView() {
//		leftViewUtil(root, 1);
//	}
//	static int maxLevel = 0;
//	private static void leftViewUtil(Node root2, int level) {
//		// TODO Auto-generated method stub
//		
//		
//		if(root2 == null)
//			return;
//		
//		if(maxLevel < level) {
//			System.out.print(root2.data + "  ");
//			maxLevel = level;
//		}
//		leftViewUtil(root2.left, level+1);
//		leftViewUtil(root2.right, level+1);
//		//System.out.println();
//		
//	}
//	static int maxLeve = 0;
//	private static void  rightView(Node root, int level) {
//		if(root == null)
//			return;
//		
//		if(maxLeve < level) {
//			System.out.print(root.data + "  ");
//			maxLeve = level;
//		}
//		rightView(root.right, level+1);
//		rightView(root.left, level+1);
//	}
//	
//	static void rightV() {
//		rightView(root, 1);
//	}
//	
//	
//	private static void  maxLevel(Vector<Integer> res, Node root, int level) {
//		if(root == null)
//			return;
//		
//		if(res.size() == level)
//			res.add(root.data);
//		else
//			res.set(level, Math.max(res.get(level), root.data));
//		maxLevel(res,root.left, level+1);
//		maxLevel(res, root.right, level+1);
//	}
//	
//	static Vector<Integer> largest(Node root){
//		Vector<Integer> res = new Vector<>();
//		maxLevel(res, root, 0);
//		return res;
//	}
//
//	public static void main(String[] args) {
//		//Vector<Integer> abc = new Vector<>();
//		LevelOrderTree tree = new LevelOrderTree();
//		tree.root = new Node(1);
//		tree.root.left = new Node(2);
//		tree.root.right = new Node(3);
//		tree.root.left.left = new Node(4);
//		tree.root.left.right = new Node(5);
//		tree.root.right.left = new Node(6);
//		tree.root.right.left.left = new Node(7);
//		tree.printNode();
//		//leftView();
//		leftViewUtil(root,1);
//		System.out.println();
//		rightView(root, 1);
//		System.out.println();
//		System.out.println("Maximum of the level");
//		Vector<Integer> abc = largest(root);
//		for(int i = 0 ; i < abc.size() ; i++) {
//			System.out.print(abc.get(i)+ "  ");
//		}
//		System.out.println();
//		System.out.println("Minimum of the level");
//		Vector<Integer> min = minimum(root);
//		for(int i = 0 ; i < min.size() ; i++) {
//			System.out.print(min.get(i)+ "  ");
//		}
//	}
//
//	private static Vector<Integer> minimum(Node root2) {
//		// TODO Auto-generated method stub
//		Vector<Integer> v = new Vector<>();
//		minimumFind(v, root, 0);
//		return v;
//	}
//
//	private static void minimumFind(Vector<Integer> v, Node root2, int i) {
//		// TODO Auto-generated method stub
//		if(root2 == null)
//			return;
//		
//		if(v.size() == i)
//			v.add(root2.data);
//		else
//			v.set(i, Math.min(v.get(i),root2.data));
//		
//		minimumFind(v, root2.left, i+1);
//		minimumFind(v, root2.right, i+1);
//	}
//}

class Node {
	int data;
	Node left;
	Node right;
	Node(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

public class LevelOrderTree{
	static Node root;
	public static void main(String[] args) {
		LevelOrderTree tree = new LevelOrderTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.left.left = new Node(7);
		tree.printNode();
		System.out.println();
		tree.leftView(root, 1);
		System.out.println();
		tree.rightView(root, 1);
		System.out.println();
		System.out.println("Maximum of the level");
		Vector<Integer> abc = largest(root);
		
	}
	
	private static Vector<Integer> largest(Node root2) {
		// TODO Auto-generated method stub
		Vector<Integer> res = new Vector<>();
		maxLevel(res, root2, 0);
		return res;
	}

	private static void maxLevel(Vector<Integer> res, Node root2, int level) {
		// TODO Auto-generated method stub
		if (root2 == null)
			return;
		if(res.size() == level) 
			res.add(root.data);
		else
			res.set(level, Math.max(res.get(level), root2.data));
		
		maxLevel(res, root2.left, level+1);
		maxLevel(res, root2.right, level+1);
	}
	
	private static void maxData(Vector<Integer> res, Node node, int level) {
		if(node == null)
			return;
//		if(res.size() == level)
//			res.add(node.data);
//		else
//			res.set(level, Math.max(node.data, res.get(level)));
		
		if(res.size() == level)
			res.add(node.data);
		else
			res.set(level, Math.max(node.data, res.get(level)));
		
		maxData(res, node.left, level+1);
		maxData(res, node.right, level+1);
	}

	static int maxlevel = 0;
	private void rightView(Node root2, int level) {
		// TODO Auto-generated method stub
//		if(root2 == null)
//			return;
//		if(maxlevel < level) {
//			System.out.println(root.data + " ");
//			maxlevel = level;
//		}
//		rightView(root2.right, level+1);
//		rightView(root2.left, level+1);
		if(root2 == null)
			return;
		if(maxlevel < level) {
			System.out.println(root.data+ " ");
			maxlevel = level;
		}
		rightView(root2.right, level+1);
		rightView(root2.left, level+1);
	}
	
	static int maxlev = 0;
	private void leftView(Node root2, int level) {
		// TODO Auto-generated method stub
		if(root2 == null)
			return;
		if(maxlev < level) {
			System.out.println(root2.data + " ");
			maxlev = level;
		}
		leftView(root2.left, level+1);
		leftView(root2.right, level+1);
	}
	private void printNode() {
		// TODO Auto-generated method stub
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			Node temp = q.poll();
			System.out.println(temp.data + " ");
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
		}System.out.println();
	}
}
