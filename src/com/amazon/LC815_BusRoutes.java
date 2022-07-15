package com.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LC815_BusRoutes {

    /*解法：BFS，先对原来的二维数组进行处理，二维数组的每一行代表这辆公交车能到达的站点，用HashMap记录某站点有哪个公交经过。
    这样处理完以后就知道每一个站点都有哪个公交车经过了。然后用一个queue记录当前站点，对于当前站点的所有经过的公交循环，每个公交又有自己的下一个站点。
    可以想象成一个图，每一个公交站点 被多少个公交经过,也就是意味着是个中转站,可以连通到另外一个公交上,
    因为题目求的是经过公交的数量而不关心经过公交站点的数量，所以在BFS的时候以公交(也就是routes的下标)来扩展。*/

    public int numBusesToDestination(int[][] routes, int source, int target) {
        // Special Cases: the source and target are same, no need bus
        if (source == target) return 0;

        //pre-processing: save <stop, List<buses>> for BFS
        Map<Integer, List<Integer>> map = new HashMap<>();
        // loop each route
        for (int i = 0; i < routes.length; i++) {
            // loop each stop in the route
            for (int stop: routes[i]) {
                // Initial the key-value pari if it does not exist
                if (!map.containsKey(stop)) {
                    map.put(stop, new ArrayList<>());
                }
                map.get(stop).add(i);
            }
        }

        // If source and target cannot be reached by any bus, return -1
        if (!map.containsKey(source) || !map.containsKey(target)) return -1;

        Set<Integer> visited = new HashSet<>(); //to store visited routes
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        int transitions = 0;
        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            // add one level
            transitions++;
            for (int i = 0; i < size; i++) {
                int curStop = queue.poll();
                List<Integer> buses = map.get(curStop);
                for (int bus: buses) {
                    // Record visited bus
                    if (visited.contains(bus)) continue;
                    visited.add(bus);
                    for (int stop: routes[bus]) {
                        if (stop == target) return transitions;
                        else queue.offer(stop);
                    }
                }
            }
        }
        return -1;
    }
}
