import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TopViewBest {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	static class QueueNode {
		TreeNode node;
		int horizontalDistance;

		QueueNode(TreeNode node, int horizontalDistance) {
			this.node = node;
			this.horizontalDistance = horizontalDistance;
		}
	}
	public static void printTopView(TreeNode root) {
		if (root == null) {
            return;
        }

        Map<Integer, Integer> topViewMap = new HashMap<>();
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(root, 0));

        while (!queue.isEmpty()) {
            QueueNode current = queue.poll();
            int horizontalDistance = current.horizontalDistance;
            
            if (!topViewMap.containsKey(horizontalDistance)) {
                topViewMap.put(horizontalDistance, current.node.val);
            }

            if (current.node.left != null) {
                queue.add(new QueueNode(current.node.left, horizontalDistance - 1));
            }

            if (current.node.right != null) {
                queue.add(new QueueNode(current.node.right, horizontalDistance + 1));
            }
        }

        for (int distance = Collections.min(topViewMap.keySet()); distance <= Collections.max(topViewMap.keySet()); distance++) {
            System.out.print(topViewMap.get(distance) + " ");
        }
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.left.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.left.right.right = new TreeNode(7);
		root.left.right.right.right = new TreeNode(8);
		root.left.right.right.right.right = new TreeNode(9);


		System.out.println("Top view of binary tree:");
		printTopView(root);
	}
}
