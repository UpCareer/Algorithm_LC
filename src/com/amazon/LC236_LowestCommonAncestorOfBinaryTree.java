package com.amazon;

public class LC236_LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case:
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left: right;

    }

   /* private TreeNode find(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode left = find(root.left, val);
        if (left != null) {
            return left;
        }

        TreeNode right = find(root.right, val);
        if (right != null) {
            return right;
        }

        return null;
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        // base case
        if (root == null) {
            return null;
        }

        if (root.val == val1 || root.val == val2) {
            return root;
        }

        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);

        return left !=null ? left:right;
    }*/
}
