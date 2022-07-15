package com.indeed.vo.whiteboard;

/** =============================================================================
Question Description:
Given a tree,(binary tree possibly) every tree edge has a cost， find the least
 cost or find the leaf node that the cost of path that from root to leaf is the least.
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** =============================================================================
code - Edge和Node2都是给好的，里面的变量类型到时候和面试官讨论吧。
=============================================================================**/


public class RootToLeafMinCost_FollowUp{

    int minCost = Integer.MAX_VALUE;
    Map<Node2, Integer> distMap = new HashMap<>();

    public List<Edge> getMinPathInGraph(Node2 root) {
        List<Edge> res = new ArrayList<>();
        List<Edge> tempPath = new ArrayList<>();
        dfsInGraph(res, tempPath, root, 0);
        return res;
    }

    public void dfsInGraph(List<Edge> res, List<Edge> temp, Node2 node, int curCost) {
        // Corner case
        if(node == null) {
            return;
        }

        // have smaller cost path from node to root
        if(distMap.containsKey(node) && curCost >= distMap.get(node)) return;
        distMap.put(node, curCost);

        // dfs End condition
        if(node.edges.size() == 0) {
            if(curCost < minCost) {
                minCost = curCost;
                res.clear();
                res.addAll(temp);
            }
            return;
        }

        for(Edge e : node.edges ) {
            Node2 next = e.node;
            temp.add(e);
            dfsInGraph(res, temp, next, curCost + e.cost);
            temp.remove(temp.size()-1);
        }
    }





}
