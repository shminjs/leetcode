package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/3.
 * 450
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference
 * (possibly updated) of the BST
 */
public class DeleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(9);
        t.left = new TreeNode(8);
        t.right = new TreeNode(10);
        t.right.left = new TreeNode(11);
        DeleteNode dn = new DeleteNode();
        TreeNode p = dn.deleteNode(t, 10);
        System.out.println(t.val);
        System.out.println(t.left.val);
        System.out.println(t.right.val);
    }
}
