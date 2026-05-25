import java.util.ArrayList;
import java.util.List;

public class TreeImpl {

	Node root;
	//List nodeValues = new ArrayList();
	static class Node{
		int key;
		Node left, right;

		public Node(int item){
			this.key = item;
			left=right=null;
		}
	}

	void printPostorder(Node node) {
		if(node == null)
			return;

		printPostorder(node.left);
		printPostorder(node.right);
		System.out.print(node.key + " ");
	}

	void printInorder(Node node) {
		if(node == null)
			return;

		printInorder(node.left);
		//nodeValues.add(node.key);
		System.out.print(node.key + " ");
		printInorder(node.right);
	}

	void printPreorder(Node node) {
		if(node == null)
			return;
		System.out.print(node.key + " ");
		printPreorder(node.left);
		printPreorder(node.right);
	}
	
	private void insert(Node root, int i) {
		// TODO Auto-generated method stub
		if(i < root.key) {
			if(root.left != null) {
				insert(root.left, i);
			}else {
				System.out.println(" Inserted " + i + " to left of " + root.key);
				root.left = new Node(i);
			}
		}else if(i > root.key) {
			if(root.right != null) {
				insert(root.right, i);
			}else {
				System.out.println(" Inserted " + i + " to right of " + root.key);
				root.right = new Node(i);
			}
		}
	}

	public static void main(String[] args) {

		TreeImpl tree = new TreeImpl();
		Node root = new Node(1);
		tree.insert(root, 2);
		tree.insert(root, 3);
		tree.insert(root, 4);
		tree.insert(root, 5);
		//		tree.insert(root, 7);
		//		tree.insert(root, 3);
		//		tree.insert(root, 9);

		System.out.println(
				"Preorder traversal of binary tree is ");
		tree.printPreorder(tree.root);

		System.out.println(
				"\nInorder traversal of binary tree is ");
		tree.printInorder(tree.root);

		System.out.println(
				"\nPostorder traversal of fbinary tree is ");
		tree.printPostorder(tree.root);
		
		
//		for(Object i : tree.nodeValues) {
//			System.out.print("nodevalues :"+i+ " ");
//		}
	}
}

	
