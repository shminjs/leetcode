package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/5.
 * 606
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that
 * don't affect the one-to-one mapping relationship between the string and the original binary tree.
 */
public class Tree2str {

    public String tree2str(TreeNode t) {
         StringBuilder sb = new StringBuilder();
         preorder(t, sb);
         return sb.toString();
    }

    private static void preorder(TreeNode t, StringBuilder sb) {
         if (t == null) {
             return;
         }
         sb.append(Integer.toString(t.val));
         if (t.right != null || t.left != null) {
             sb.append("(");
             preorder(t.left, sb);
             sb.append(")");
         }
         if (t.right != null) {
             sb.append("(");
             preorder(t.right, sb);
             sb.append(")");
         }
    }

    public static void main(String[] args) {
         TreeNode t = new TreeNode(1);
         t.left = new TreeNode(2);
         t.right = new TreeNode(3);
//         t.left.left = new TreeNode(4);
        t.left.right = new TreeNode(4);
         Tree2str ts = new Tree2str();
         String result = ts.tree2str(t);
         System.out.println(result);
    }
}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
