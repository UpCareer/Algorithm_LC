package com.amazon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class LC103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // From left to right
        boolean flag = true;

        while(!q.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            // Traversal same level TreeNode
            int size = q.size();
            for (int i=0; i < size; i++) {
                TreeNode cur = q.poll();
                if(flag) {
                    level.addLast(cur.val);
                } else {
                    level.addFirst(cur.val);
                }
                // add next level TreeNode to queue
                if(cur.left != null) {
                    q.offer(cur.left);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                }
            }
            // change direction and add current level result to Res
            flag = !flag;
            res.add(level);
        }
        return res;
    }
}
