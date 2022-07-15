package com.lc.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words,
 * any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi]
 * indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node
 * of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible
 * rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 *
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 *  3 : 0->1->2->4
 *  5 : 4
 */

public class LC310_MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(n == 0) {
            return res;
        }
        if(n == 1) {
            res.add(0);
            return res;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[n];
        Queue<Integer> q = new LinkedList<>();

        //
        for(int[] edge : edges){
            if(!graph.containsKey(edge[0])){
                graph.put(edge[0], new ArrayList<Integer>());
            }
            graph.get(edge[0]).add(edge[1]);
            if(!graph.containsKey(edge[1])){
                graph.put(edge[1], new ArrayList<Integer>());
            }
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }

        for(int i = 0; i < n; i++) {
            if(indegree[i] == 1) {
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int len = q.size();
            res = new ArrayList<Integer>();

            for(int i = 0; i < len; i++){
                int curr = q.poll();
                res.add(curr);
                for(Integer x : graph.get(curr)){
                    indegree[x]--;
                    if(indegree[x] == 1){
                        q.add(x);
                    }
                }
            }
        }
        return res;
    }
}
