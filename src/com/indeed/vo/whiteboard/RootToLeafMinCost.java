package com.indeed.vo.whiteboard;

/** =============================================================================
Question Description:
Given a tree,(binary tree possibly) every tree edge has a cost， find the least
 cost or find the leaf node that the cost of path that from root to leaf is the least.
 **/

import java.util.ArrayList;
import java.util.List;

/** =============================================================================
code - Edge和Node2都是给好的，里面的变量类型到时候和面试官讨论吧。
=============================================================================**/

class Edge{
    Node2 node; //表示这个edge的尾巴指向哪里。
    int cost;
    public Edge(Node2 n, int cost) {
        this.node = n;
        this.cost = cost;
    }
}
class Node2 {
    List<Edge> edges; //表示从这个头出发的所有edge
    public Node2(){
        this.edges = new ArrayList<>();
    }
}

public class RootToLeafMinCost {

    int minCost = Integer.MAX_VALUE;

    //返回最短路径上面的所有Edge
    public List<Edge> getMinPath(Node2 root){
        List<Edge> res = new ArrayList<>();
        List<Edge> temp = new ArrayList<>();
        dfs(res, temp, root, 0);
        return res;
    }

    //就是普通的DFS
    public void dfs(List<Edge> res, List<Edge> temp, Node2 root, int curCost){
        if (root == null){
            return;
        }

        // reach the leaf: DFS ending condition
        if (root.edges.size() == 0){
            if (curCost < minCost){
                minCost = curCost;
                res.clear();
                res.addAll(temp);
                return;
            }
        }

        // General Logic
        for (Edge e : root.edges){
            Node2 next = e.node;
            temp.add(e);
            dfs(res, temp, next, curCost+e.cost);
            temp.remove(temp.size()-1);
        }
    }

    //这个只返回个最小cost
    public int getMinCost(Node2 root){
        if (root == null) {
            return 0;
        }
        helper(root, 0);
        return minCost;
    }
    public void helper(Node2 root, int curCost){
        if (root.edges.size() == 0){
            minCost = Math.min(minCost, curCost);
            return;
        }
        for (Edge e : root.edges){
            Node2 next = e.node;
            helper(next, curCost + e.cost);
        }
    }

}
