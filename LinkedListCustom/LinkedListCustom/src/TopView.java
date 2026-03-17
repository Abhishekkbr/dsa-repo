import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

public class TopView {
	
	static class Node
    {
        int key;
        Node left;
        Node right;
         
        // Constructor
        Node(int data)
        {
            key = data;
            left = null;
            right = null;
        }
    }
	
	public static void main(String[] args) {
		Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//        root.right.left.right = new Node(8);
//        root.right.right.right = new Node(9);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.left.left = new Node(7);
        System.out.println("Vertical Order traversal is");
        printVerticalOrder(root);
	}

	private static void printVerticalOrder(Node root) {
		// TODO Auto-generated method stub
		TreeMap<Integer,Integer> m = new TreeMap<>();
        int hd =0;
        getVerticalOrder(root,hd,m);
         
       
        for (Entry<Integer, Integer> entry : m.entrySet())
        {
            System.out.print(entry.getValue().toString() + " ");
        }
	}

	private static void getVerticalOrder(Node root, int hd, TreeMap<Integer, Integer> m) {
		// TODO Auto-generated method stub
		 if(root == null)
	            return;
	         
	        
	        Integer get =  m.get(hd);
	        
	        if(get == null)
	        {
	            
	        	m.put(hd, root.key);
	        }

	        getVerticalOrder(root.left, hd-1, m);
	         
	        // Store nodes in right subtree
	        getVerticalOrder(root.right, hd+1, m);
	}
}
