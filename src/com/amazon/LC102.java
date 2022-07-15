package com.amazon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int size = q.size();
            // Traversal current level and add next level to queue
            for(int i=0; i < size; i++) {
                TreeNode cur = q.poll();
                level.add(cur.val);
                if(cur.left != null) {
                    q.offer(cur.left);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                }
            }
            // add current level to Res
            res.add(level);
        }
        return res;
    }
}
