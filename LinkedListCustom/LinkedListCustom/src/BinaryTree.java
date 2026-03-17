
public class BinaryTree {
	static class Node{
		int data;
		Node left;
		Node right;
		Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	public static void main(String args[]) 
	{ 
		BinaryTree tree = new BinaryTree();
		Node root = new Node(5);
		System.out.println("Binary Tree Example");
		System.out.println("Building tree with root value " + root.data);
		tree.insert(root, 2);
		tree.insert(root, 4);
		tree.insert(root, 8);
		tree.insert(root, 6);
		tree.insert(root, 7);
		tree.insert(root, 3);
		tree.insert(root, 9);
		System.out.println("Traversing tree in order: ");
		tree.traverseLevelOrder(root);
		System.out.println();
		System.out.println("Traverse tree pre order: ");
		tree.printPreorder(root);
		System.out.println();
		System.out.println("Traverse tree post order: ");
		tree.printPostorder(root);
		System.out.println();
		System.out.println("Traverse tree in order: ");
		tree.printInorder(root);

	}
	
	private void printInorder(Node root) {
		// TODO Auto-generated method stub
		if(root == null)
			return;
		
		printInorder(root.left);
		System.out.print(root.data+" ");
		printInorder(root.right);	
	}
	
	
	private void printPostorder(Node root) {
		// TODO Auto-generated method stub
		if(root == null)
			return;
		printPostorder(root.left);
		printPostorder(root.right);
		System.out.print(root.data + " ");
	}
	private void printPreorder(Node root) {
		// TODO Auto-generated method stub
		if(root == null)
			return;
		System.out.print(root.data + " ");
		printPreorder(root.left);
		printPreorder(root.right);
		
	}
	private void insert(Node root, int i) {
		// TODO Auto-generated method stub
		if(i < root.data) {
			if(root.left != null) {
				insert(root.left, i);
			}else {
				System.out.println(" Inserted " + i + " to left of " + root.data);
				root.left = new Node(i);
			}
		}else if(i > root.data) {
			if(root.right!=null) {
				insert(root.right, i);
			}else {
				System.out.println(" Inserted " + i + " to right of " + root.data);
				root.right = new Node(i);
			}
		}
		
	}
	private void traverseLevelOrder(Node r) {
		// TODO Auto-generated method stub
		if(r!=null) {
			traverseLevelOrder(r.left);
			System.out.print(" "+r.data);
			traverseLevelOrder(r.right);
		}
		
	}
}
