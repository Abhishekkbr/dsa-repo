import java.util.Stack;

public class ZigZagTree {


	static class Node{
		int data;
		Node leftChild;
		Node rightChild;
		Node(int data){
			this.data = data;
		}
	}


	static class BinaryTree{
		Node rootNode;

		public void printZigZagTraversal() {
			// TODO Auto-generated method stub
			if(rootNode == null)
				return;
			Stack<Node> currLevel = new Stack<>();
			Stack<Node> nextLevel = new Stack<>();
			currLevel.push(rootNode);
			boolean leftToRight = true;
			while(!currLevel.isEmpty()) {
				Node val = currLevel.pop();
				System.out.print(val.data + "  ");
				
				if(leftToRight) {
					if(val.leftChild != null)
						nextLevel.push(val.leftChild);
					if(val.rightChild != null)
						nextLevel.push(val.rightChild);
				}else {
					if(val.rightChild != null)
						nextLevel.push(val.rightChild);
					if(val.leftChild != null)
						nextLevel.push(val.leftChild);
				}
				
				if(currLevel.isEmpty()) {
					leftToRight = !leftToRight;
					Stack<Node> temp = currLevel;
					currLevel = nextLevel;
					nextLevel = temp;
				}
				
			} 
		}
	}

	public static void main(String[] args)
	{
		BinaryTree tree = new BinaryTree();
		tree.rootNode = new Node(1);
		tree.rootNode.leftChild = new Node(2);
		tree.rootNode.rightChild = new Node(3);
		tree.rootNode.leftChild.leftChild = new Node(7);
		tree.rootNode.leftChild.rightChild = new Node(6);
		tree.rootNode.rightChild.leftChild = new Node(5);
		tree.rootNode.rightChild.rightChild = new Node(4);

		System.out.println("ZigZag Order traversal of binary tree is");
		tree.printZigZagTraversal();
	}

}

