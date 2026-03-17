
public class SparseTreeToBinary {
	static class Node{
		int data;
		Node left, right;
		Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	public static void insert(Node root, int i) {
		if(i < root.data) {
			if(root.left != null)
				insert(root.left, i);
			else {
				root.left = new Node(i);
			}
		}else {
			if(root.right != null){
				insert(root.right, i);
			}else {
				root.right = new Node(i);
			}
		}
	}
	
	public static boolean isBst(Node node) {
		return isBinary(node, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}
	
	private static boolean isBinary(Node node, int maxValue, int minValue) {
		// TODO Auto-generated method stub
		if(node == null)
			return true;
		if(node.data < minValue || node.data > maxValue) {
			return false;
		}
		return (isBinary(node.left, node.data, minValue) && isBinary(node.right, maxValue, node.data));
	}

	public static void main(String[] args) {
		SparseTreeToBinary tree = new SparseTreeToBinary();
		Node root = new Node(2);
		//tree.insert(root, 2);
        tree.insert(root, 4);
        tree.insert(root, 8);
        tree.insert(root, 6);
        tree.insert(root, 7);
        tree.insert(root, 9);
        tree.insert(root, 5);
        tree.insert(root, 3);
		
		System.out.println(
				"Preorder traversal of binary tree is ");
		tree.printPreorder(root);
		boolean isbinary = isBst(root);
		System.out.println("Is Binary : "+isbinary);
		
	}

	private void printPreorder(Node root) {
		// TODO Auto-generated method stub
		if(root == null)
			return;
		
		printPreorder(root.left);
		System.out.println(root.data);
		printPreorder(root.right);
	}
}
