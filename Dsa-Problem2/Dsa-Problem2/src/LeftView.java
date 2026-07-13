import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeftView {
	static class TreeNode{
		int data;
		TreeNode left, right;
		
		public TreeNode(int data) {
			// TODO Auto-generated constructor stub
			this.data = data;
			left = right = null;
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(6);

        System.out.println("Left view of the given tree:");
        leftView(root);
	}

	private static void leftView(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null)
			return;
		List<Integer> result = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
//		while(!q.isEmpty()) {
//			int levelSize = q.size();
//			for(int i = 0 ; i < levelSize ; i++) {
//				TreeNode curr = q.poll();
//				if(i == 0)   // for right view levelSize-1
//					result.add(curr.data);
//				if(curr.left != null)
//					q.offer(curr.left);
//				if(curr.right != null)
//					q.offer(curr.right);
//			}
//		}
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0 ; i < size ; i++) {
				TreeNode data = q.poll();
				if(i == 0) {
					result.add(data.data);
				}if(data.left != null) {
					q.offer(data.left);
				}
				if(data.right != null) {
					q.offer(data.right);
				}
			}
		}
		result.forEach(System.out::println);
	}
}
