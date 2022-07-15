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
import java.util.PriorityQueue;

/** =============================================================================
code - Edge和Node2都是给好的，里面的变量类型到时候和面试官讨论吧。
=============================================================================**/


public class RootToLeafMinCost_FollowUp_dijkstra {

    public List<Edge> getMinPathInDAG(Node2 root) {
        int minCost = Integer.MAX_VALUE;
        // use this node to record the leaf node, then can backtrace to the root and get the full path
        Node2 resNode = null;
        List<Edge> res = new ArrayList<>();
        Map<Node2, Integer> dist = new HashMap<>();
        // keep parent - child relationship : Key is child and value is parent
        Map<Node2, Node2> findParent = new HashMap<>();
        // Min Heap - to select next most promising node
        PriorityQueue<Node2> pq = new PriorityQueue<>((o1, o2) -> dist.get(o1) - dist.get(o2));

        dist.put(root, 0);
        findParent.put(root, null);
        pq.offer(root);
        while(!pq.isEmpty()) {
            // Get the next promising node from Heap
            Node2 cur = pq.poll();
            int d = dist.get(cur);
            // Find one path from root to leaf
            if(cur.edges.size() == 0) {
                int curCost = dist.get(cur);
                if(curCost < minCost) {
                    minCost = curCost;
                    resNode = cur;
                }
                break;
            }
            for(Edge e : cur.edges) {
                Node2 next = e.node;
                int tempDist = e.cost + d;
                // visit the node for the first time
                if(!dist.containsKey(next)) {
                    dist.put(next, tempDist);
                    findParent.put(next, cur);
                    pq.offer(next);
                } else {
                    // revisit the node with the shorter distance
                    if(tempDist < dist.get(next)) {
                        pq.remove(next);
                        dist.put(next, tempDist);
                        findParent.put(next,cur);
                        pq.offer(next);
                    }
                }
            }
        }

        //下面都是为了输出所有的边才写的，就是把所有的node都抓出来，然后去找连接的边
        List<Node2> tempList = new ArrayList<>();
        while (resNode != null){
            tempList.add(0, resNode);
            resNode = findParent.get(resNode);
        }

        for (int i = 0; i < tempList.size()-1; i++){
            Node2 cur = tempList.get(i);
            for (Edge e : cur.edges) {
                if (e.node.equals(tempList.get(i+1))){
                    res.add(e);
                }
            }
        }
        return res;
    }
}
