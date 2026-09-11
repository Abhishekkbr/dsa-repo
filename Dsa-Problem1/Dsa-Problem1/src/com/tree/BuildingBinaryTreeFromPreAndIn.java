package com.tree;

import java.util.HashMap;
import java.util.Map;

public class BuildingBinaryTreeFromPreAndIn {

    static int preIndex;
    static Map<Integer, Integer> count = new HashMap<>();

    static class TreeNode {
        TreeNode left, right;
        int data;

        TreeNode(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = buildTree(preorder, inorder);

        System.out.print("In-order of built tree: ");
        printInOrder(root);   // should print: 9 3 15 20 7 (matches input inorder -- confirms correctness)
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            count.put(inorder[i], i);
        }
        return buildSubTree(preorder, 0, inorder.length - 1);
    }

    private static TreeNode buildSubTree(int[] preorder, int startIndex, int endIndex) {
        if (startIndex > endIndex) return null;

        int rootNode = preorder[preIndex++];

        TreeNode root = new TreeNode(rootNode);
        int midIndex = count.get(rootNode);

        root.left = buildSubTree(preorder, startIndex, midIndex - 1);
        root.right = buildSubTree(preorder, midIndex + 1, endIndex);

        return root;
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }
}

